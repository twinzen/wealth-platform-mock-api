package com.wealth.mockapi.controller;

import com.wealth.mockapi.dto.response.ApiResponse;
import com.wealth.mockapi.model.PortfolioHolding;
import com.wealth.mockapi.service.PortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolios")
@RequiredArgsConstructor
@Tag(name = "Portfolio", description = "Portfolio holdings management")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping("/{accountId}/holdings")
    @Operation(summary = "Get all holdings for an account")
    public ResponseEntity<ApiResponse<List<PortfolioHolding>>> getHoldings(
            @Parameter(description = "Account ID", example = "ACC-001")
            @PathVariable String accountId) {

        List<PortfolioHolding> holdings = portfolioService.getHoldings(accountId);
        return ResponseEntity.ok(ApiResponse.ok(holdings));
    }

    @GetMapping("/{accountId}/holdings/{symbol}")
    @Operation(summary = "Get a specific holding by symbol")
    public ResponseEntity<ApiResponse<PortfolioHolding>> getHolding(
            @Parameter(description = "Account ID", example = "ACC-001")
            @PathVariable String accountId,
            @Parameter(description = "Stock symbol", example = "AAPL")
            @PathVariable String symbol) {

        PortfolioHolding holding = portfolioService.getHolding(accountId, symbol);
        return ResponseEntity.ok(ApiResponse.ok(holding));
    }
}
