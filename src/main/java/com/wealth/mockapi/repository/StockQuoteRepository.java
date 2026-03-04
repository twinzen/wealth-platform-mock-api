package com.wealth.mockapi.repository;

import com.wealth.mockapi.model.StockQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockQuoteRepository extends JpaRepository<StockQuote, Long> {

    Optional<StockQuote> findBySymbol(String symbol);

    List<StockQuote> findBySymbolIn(List<String> symbols);

    boolean existsBySymbol(String symbol);
}
