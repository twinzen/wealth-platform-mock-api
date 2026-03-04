package com.wealth.mockapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "portfolio_holdings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"account_id", "symbol"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioHolding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(nullable = false, length = 20)
    private String symbol;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "asset_class", length = 20)
    private String assetClass;

    @Column(nullable = false, precision = 18, scale = 6)
    private BigDecimal quantity;

    @Column(name = "avg_cost_price", nullable = false, precision = 18, scale = 6)
    private BigDecimal avgCostPrice;

    @Column(name = "current_price", nullable = false, precision = 18, scale = 6)
    private BigDecimal currentPrice;

    @Column(name = "market_value", nullable = false, precision = 18, scale = 2)
    private BigDecimal marketValue;

    @Column(name = "unrealized_gain_loss", precision = 18, scale = 2)
    private BigDecimal unrealizedGainLoss;

    @Column(name = "unrealized_gain_loss_pct", precision = 8, scale = 4)
    private BigDecimal unrealizedGainLossPct;

    @Column(length = 10)
    @Builder.Default
    private String currency = "USD";

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}
