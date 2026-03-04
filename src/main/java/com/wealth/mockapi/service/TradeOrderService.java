package com.wealth.mockapi.service;

import com.wealth.mockapi.dto.request.PlaceOrderRequest;
import com.wealth.mockapi.dto.request.UpdateOrderRequest;
import com.wealth.mockapi.exception.InvalidOperationException;
import com.wealth.mockapi.exception.ResourceNotFoundException;
import com.wealth.mockapi.model.TradeOrder;
import com.wealth.mockapi.model.enums.OrderStatus;
import com.wealth.mockapi.model.enums.OrderType;
import com.wealth.mockapi.repository.TradeOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class TradeOrderService {

    private final TradeOrderRepository orderRepository;

    private static final AtomicLong ORDER_SEQUENCE = new AtomicLong(1000);

    private static final DateTimeFormatter ORDER_DATE_FMT =
            DateTimeFormatter.ofPattern("yyyyMMdd");

    /** Statuses that cannot be modified or cancelled. */
    private static final List<OrderStatus> TERMINAL_STATUSES = List.of(
            OrderStatus.FILLED, OrderStatus.CANCELLED, OrderStatus.REJECTED, OrderStatus.EXPIRED);

    @Transactional
    public TradeOrder placeOrder(PlaceOrderRequest req) {
        validateOrderRequest(req);

        String orderId = generateOrderId();

        TradeOrder order = TradeOrder.builder()
                .orderId(orderId)
                .accountId(req.getAccountId())
                .symbol(req.getSymbol().toUpperCase())
                .orderType(req.getOrderType())
                .side(req.getSide())
                .quantity(req.getQuantity())
                .limitPrice(req.getLimitPrice())
                .stopPrice(req.getStopPrice())
                .timeInForce(req.getTimeInForce() != null ? req.getTimeInForce() :
                        com.wealth.mockapi.model.enums.TimeInForce.DAY)
                .notes(req.getNotes())
                .status(OrderStatus.OPEN)
                .build();

        return orderRepository.save(order);
    }

    public TradeOrder getOrder(String orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", orderId));
    }

    public List<TradeOrder> getOrdersByAccount(String accountId) {
        return orderRepository.findByAccountId(accountId);
    }

    public List<TradeOrder> getOrdersByAccountAndStatus(String accountId, OrderStatus status) {
        return orderRepository.findByAccountIdAndStatus(accountId, status);
    }

    @Transactional
    public TradeOrder updateOrder(String orderId, UpdateOrderRequest req) {
        TradeOrder order = getOrderAndAssertModifiable(orderId);

        if (req.getQuantity() != null) {
            order.setQuantity(req.getQuantity());
        }
        if (req.getLimitPrice() != null) {
            order.setLimitPrice(req.getLimitPrice());
        }
        if (req.getStopPrice() != null) {
            order.setStopPrice(req.getStopPrice());
        }
        if (req.getTimeInForce() != null) {
            order.setTimeInForce(req.getTimeInForce());
        }
        if (req.getNotes() != null) {
            order.setNotes(req.getNotes());
        }

        return orderRepository.save(order);
    }

    @Transactional
    public TradeOrder cancelOrder(String orderId) {
        TradeOrder order = getOrderAndAssertModifiable(orderId);
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private TradeOrder getOrderAndAssertModifiable(String orderId) {
        TradeOrder order = getOrder(orderId);
        if (TERMINAL_STATUSES.contains(order.getStatus())) {
            throw new InvalidOperationException(
                    "Order " + orderId + " is in terminal status " + order.getStatus()
                            + " and cannot be modified or cancelled");
        }
        return order;
    }

    private void validateOrderRequest(PlaceOrderRequest req) {
        OrderType type = req.getOrderType();
        if ((type == OrderType.LIMIT || type == OrderType.STOP_LIMIT) && req.getLimitPrice() == null) {
            throw new IllegalArgumentException("Limit price is required for " + type + " orders");
        }
        if ((type == OrderType.STOP || type == OrderType.STOP_LIMIT) && req.getStopPrice() == null) {
            throw new IllegalArgumentException("Stop price is required for " + type + " orders");
        }
    }

    private String generateOrderId() {
        String date = LocalDateTime.now().format(ORDER_DATE_FMT);
        long seq = ORDER_SEQUENCE.getAndIncrement();
        return "ORD-" + date + "-" + String.format("%06d", seq);
    }
}
