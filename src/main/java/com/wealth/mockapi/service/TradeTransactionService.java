package com.wealth.mockapi.service;

import com.wealth.mockapi.exception.ResourceNotFoundException;
import com.wealth.mockapi.model.TradeTransaction;
import com.wealth.mockapi.model.enums.TransactionType;
import com.wealth.mockapi.repository.TradeTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TradeTransactionService {

    private final TradeTransactionRepository transactionRepository;

    public Page<TradeTransaction> getTransactions(String accountId, Pageable pageable) {
        return transactionRepository.findByAccountId(accountId, pageable);
    }

    public Page<TradeTransaction> getTransactionsByType(
            String accountId, TransactionType type, Pageable pageable) {
        return transactionRepository.findByAccountIdAndTransactionType(accountId, type, pageable);
    }

    public TradeTransaction getTransaction(String accountId, String transactionId) {
        TradeTransaction tx = transactionRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", transactionId));
        if (!tx.getAccountId().equals(accountId)) {
            throw new ResourceNotFoundException("Transaction", transactionId);
        }
        return tx;
    }

    public List<TradeTransaction> getTransactionsBySymbol(String accountId, String symbol) {
        return transactionRepository.findByAccountIdAndSymbol(accountId, symbol.toUpperCase());
    }
}
