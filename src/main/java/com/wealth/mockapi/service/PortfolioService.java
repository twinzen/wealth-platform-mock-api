package com.wealth.mockapi.service;

import com.wealth.mockapi.exception.ResourceNotFoundException;
import com.wealth.mockapi.model.PortfolioHolding;
import com.wealth.mockapi.repository.PortfolioHoldingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioHoldingRepository holdingRepository;

    public List<PortfolioHolding> getHoldings(String accountId) {
        return holdingRepository.findByAccountId(accountId);
    }

    public PortfolioHolding getHolding(String accountId, String symbol) {
        return holdingRepository.findByAccountIdAndSymbol(accountId, symbol.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Holding not found for account " + accountId + " and symbol " + symbol));
    }
}
