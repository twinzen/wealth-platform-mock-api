package com.wealth.mockapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_quotes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String symbol;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(length = 20)
    private String exchange;

    @Column(length = 50)
    private String sector;

    @Column(name = "current_price", nullable = false, precision = 18, scale = 6)
    private BigDecimal currentPrice;

    @Column(name = "previous_close", precision = 18, scale = 6)
    private BigDecimal previousClose;

    @Column(name = "open_price", precision = 18, scale = 6)
    private BigDecimal openPrice;

    @Column(name = "high_price", precision = 18, scale = 6)
    private BigDecimal highPrice;

    @Column(name = "low_price", precision = 18, scale = 6)
    private BigDecimal lowPrice;

    @Column
    private Long volume;

    @Column(name = "avg_volume")
    private Long avgVolume;

    @Column(name = "market_cap", precision = 20, scale = 2)
    private BigDecimal marketCap;

    @Column(name = "pe_ratio", precision = 10, scale = 4)
    private BigDecimal peRatio;

    @Column(name = "dividend_yield", precision = 8, scale = 4)
    private BigDecimal dividendYield;

    @Column(name = "price_change", precision = 18, scale = 6)
    private BigDecimal priceChange;

    @Column(name = "price_change_pct", precision = 8, scale = 4)
    private BigDecimal priceChangePct;

    @Column(length = 10)
    @Builder.Default
    private String currency = "USD";

    @Column(name = "last_updated")
    @Builder.Default
    private LocalDateTime lastUpdated = LocalDateTime.now();
}
