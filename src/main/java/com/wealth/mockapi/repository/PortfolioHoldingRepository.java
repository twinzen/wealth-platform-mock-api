package com.wealth.mockapi.repository;

import com.wealth.mockapi.model.PortfolioHolding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioHoldingRepository extends JpaRepository<PortfolioHolding, Long> {

    List<PortfolioHolding> findByAccountId(String accountId);

    Optional<PortfolioHolding> findByAccountIdAndSymbol(String accountId, String symbol);

    boolean existsByAccountIdAndSymbol(String accountId, String symbol);
}
