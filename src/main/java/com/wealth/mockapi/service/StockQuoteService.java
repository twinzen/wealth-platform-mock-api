package com.wealth.mockapi.service;

import com.wealth.mockapi.exception.ResourceNotFoundException;
import com.wealth.mockapi.model.StockQuote;
import com.wealth.mockapi.repository.StockQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockQuoteService {

    private final StockQuoteRepository quoteRepository;

    public StockQuote getQuote(String symbol) {
        return quoteRepository.findBySymbol(symbol.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException("Stock quote", symbol.toUpperCase()));
    }

    public List<StockQuote> getQuotes(List<String> symbols) {
        List<String> upperSymbols = symbols.stream().map(String::toUpperCase).toList();
        return quoteRepository.findBySymbolIn(upperSymbols);
    }

    public List<StockQuote> getAllQuotes() {
        return quoteRepository.findAll();
    }
}
