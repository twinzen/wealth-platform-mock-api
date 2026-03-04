package com.wealth.mockapi.controller;

import com.wealth.mockapi.dto.response.ApiResponse;
import com.wealth.mockapi.dto.response.PagedResponse;
import com.wealth.mockapi.model.TradeTransaction;
import com.wealth.mockapi.model.enums.TransactionType;
import com.wealth.mockapi.service.TradeTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Trade Transactions", description = "Trade transaction history")
public class TradeTransactionController {

    private final TradeTransactionService transactionService;

    @GetMapping("/{accountId}/transactions")
    @Operation(summary = "Get paginated trade transactions for an account")
    public ResponseEntity<ApiResponse<PagedResponse<TradeTransaction>>> getTransactions(
            @Parameter(description = "Account ID", example = "ACC-001")
            @PathVariable String accountId,
            @Parameter(description = "Filter by transaction type (BUY, SELL, DIVIDEND, etc.)")
            @RequestParam(required = false) TransactionType type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Direction.DESC, "transactionDate"));

        Page<TradeTransaction> result = (type == null)
                ? transactionService.getTransactions(accountId, pageable)
                : transactionService.getTransactionsByType(accountId, type, pageable);

        return ResponseEntity.ok(ApiResponse.ok(PagedResponse.of(result)));
    }

    @GetMapping("/{accountId}/transactions/{transactionId}")
    @Operation(summary = "Get a specific transaction by transaction ID")
    public ResponseEntity<ApiResponse<TradeTransaction>> getTransaction(
            @Parameter(description = "Account ID", example = "ACC-001")
            @PathVariable String accountId,
            @Parameter(description = "Transaction ID", example = "TXN-20240101-000001")
            @PathVariable String transactionId) {

        return ResponseEntity.ok(ApiResponse.ok(
                transactionService.getTransaction(accountId, transactionId)));
    }

    @GetMapping("/{accountId}/transactions/symbol/{symbol}")
    @Operation(summary = "Get all transactions for a specific symbol")
    public ResponseEntity<ApiResponse<List<TradeTransaction>>> getTransactionsBySymbol(
            @Parameter(description = "Account ID", example = "ACC-001")
            @PathVariable String accountId,
            @Parameter(description = "Stock symbol", example = "AAPL")
            @PathVariable String symbol) {

        return ResponseEntity.ok(ApiResponse.ok(
                transactionService.getTransactionsBySymbol(accountId, symbol)));
    }
}
