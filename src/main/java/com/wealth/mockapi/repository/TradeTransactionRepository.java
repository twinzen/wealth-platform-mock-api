package com.wealth.mockapi.repository;

import com.wealth.mockapi.model.TradeTransaction;
import com.wealth.mockapi.model.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeTransactionRepository extends JpaRepository<TradeTransaction, Long> {

    Optional<TradeTransaction> findByTransactionId(String transactionId);

    Page<TradeTransaction> findByAccountId(String accountId, Pageable pageable);

    List<TradeTransaction> findByAccountIdAndSymbol(String accountId, String symbol);

    List<TradeTransaction> findByOrderId(String orderId);

    Page<TradeTransaction> findByAccountIdAndTransactionType(
            String accountId, TransactionType transactionType, Pageable pageable);
}
