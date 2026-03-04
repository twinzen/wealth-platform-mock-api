package com.wealth.mockapi.repository;

import com.wealth.mockapi.model.TradeOrder;
import com.wealth.mockapi.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeOrderRepository extends JpaRepository<TradeOrder, Long> {

    Optional<TradeOrder> findByOrderId(String orderId);

    List<TradeOrder> findByAccountId(String accountId);

    List<TradeOrder> findByAccountIdAndStatus(String accountId, OrderStatus status);

    List<TradeOrder> findByAccountIdAndSymbol(String accountId, String symbol);

    boolean existsByOrderId(String orderId);
}
