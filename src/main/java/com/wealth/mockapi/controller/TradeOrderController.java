package com.wealth.mockapi.controller;

import com.wealth.mockapi.dto.request.PlaceOrderRequest;
import com.wealth.mockapi.dto.request.UpdateOrderRequest;
import com.wealth.mockapi.dto.response.ApiResponse;
import com.wealth.mockapi.model.TradeOrder;
import com.wealth.mockapi.model.enums.OrderStatus;
import com.wealth.mockapi.service.TradeOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Trade Orders", description = "Trade order placement and management")
public class TradeOrderController {

    private final TradeOrderService orderService;

    @PostMapping
    @Operation(summary = "Place a new trade order")
    public ResponseEntity<ApiResponse<TradeOrder>> placeOrder(
            @Valid @RequestBody PlaceOrderRequest request) {

        TradeOrder order = orderService.placeOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok(order, "Order placed successfully"));
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get trade order status by order ID")
    public ResponseEntity<ApiResponse<TradeOrder>> getOrder(
            @Parameter(description = "Order ID", example = "ORD-20240101-001000")
            @PathVariable String orderId) {

        return ResponseEntity.ok(ApiResponse.ok(orderService.getOrder(orderId)));
    }

    @GetMapping
    @Operation(summary = "Get all orders for an account, optionally filtered by status")
    public ResponseEntity<ApiResponse<List<TradeOrder>>> getOrdersByAccount(
            @Parameter(description = "Account ID", example = "ACC-001")
            @RequestParam String accountId,
            @Parameter(description = "Filter by order status")
            @RequestParam(required = false) OrderStatus status) {

        List<TradeOrder> orders = (status == null)
                ? orderService.getOrdersByAccount(accountId)
                : orderService.getOrdersByAccountAndStatus(accountId, status);

        return ResponseEntity.ok(ApiResponse.ok(orders));
    }

    @PatchMapping("/{orderId}")
    @Operation(summary = "Update an open trade order (quantity, prices, time-in-force)")
    public ResponseEntity<ApiResponse<TradeOrder>> updateOrder(
            @Parameter(description = "Order ID", example = "ORD-20240101-001000")
            @PathVariable String orderId,
            @Valid @RequestBody UpdateOrderRequest request) {

        TradeOrder updated = orderService.updateOrder(orderId, request);
        return ResponseEntity.ok(ApiResponse.ok(updated, "Order updated successfully"));
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "Cancel a trade order")
    public ResponseEntity<ApiResponse<TradeOrder>> cancelOrder(
            @Parameter(description = "Order ID", example = "ORD-20240101-001000")
            @PathVariable String orderId) {

        TradeOrder cancelled = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(ApiResponse.ok(cancelled, "Order cancelled successfully"));
    }
}
