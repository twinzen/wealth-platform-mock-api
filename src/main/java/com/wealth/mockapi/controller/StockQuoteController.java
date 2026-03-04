package com.wealth.mockapi.controller;

import com.wealth.mockapi.dto.response.ApiResponse;
import com.wealth.mockapi.model.StockQuote;
import com.wealth.mockapi.service.StockQuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor
@Tag(name = "Stock Quotes", description = "Real-time stock quote data")
public class StockQuoteController {

    private final StockQuoteService quoteService;

    @GetMapping
    @Operation(summary = "Get quotes – all or filtered by comma-separated symbols")
    public ResponseEntity<ApiResponse<List<StockQuote>>> getQuotes(
            @Parameter(description = "Comma-separated symbols, e.g. AAPL,MSFT,GOOGL")
            @RequestParam(required = false) List<String> symbols) {

        List<StockQuote> quotes = (symbols == null || symbols.isEmpty())
                ? quoteService.getAllQuotes()
                : quoteService.getQuotes(symbols);

        return ResponseEntity.ok(ApiResponse.ok(quotes));
    }

    @GetMapping("/{symbol}")
    @Operation(summary = "Get quote for a single symbol")
    public ResponseEntity<ApiResponse<StockQuote>> getQuote(
            @Parameter(description = "Stock symbol", example = "AAPL")
            @PathVariable String symbol) {

        return ResponseEntity.ok(ApiResponse.ok(quoteService.getQuote(symbol)));
    }
}
