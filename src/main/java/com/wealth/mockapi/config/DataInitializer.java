package com.wealth.mockapi.config;

import com.wealth.mockapi.model.*;
import com.wealth.mockapi.model.enums.*;
import com.wealth.mockapi.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final StockQuoteRepository quoteRepository;
    private final PortfolioHoldingRepository holdingRepository;
    private final TradeTransactionRepository transactionRepository;
    private final TradeOrderRepository orderRepository;

    @Override
    public void run(String... args) {
        seedStockQuotes();
        seedPortfolioHoldings();
        seedTradeTransactions();
        seedTradeOrders();
        log.info("Mock data initialization complete.");
    }

    // -------------------------------------------------------------------------
    // Stock Quotes
    // -------------------------------------------------------------------------
    private void seedStockQuotes() {
        List<StockQuote> quotes = List.of(
                StockQuote.builder()
                        .symbol("AAPL").companyName("Apple Inc.").exchange("NASDAQ").sector("Technology")
                        .currentPrice(bd("189.30")).previousClose(bd("187.15")).openPrice(bd("187.50"))
                        .highPrice(bd("190.45")).lowPrice(bd("186.80")).volume(52_345_678L).avgVolume(61_000_000L)
                        .marketCap(bd("2950000000000")).peRatio(bd("30.25")).dividendYield(bd("0.55"))
                        .priceChange(bd("2.15")).priceChangePct(bd("1.15")).currency("USD")
                        .lastUpdated(LocalDateTime.now()).build(),

                StockQuote.builder()
                        .symbol("MSFT").companyName("Microsoft Corporation").exchange("NASDAQ").sector("Technology")
                        .currentPrice(bd("415.20")).previousClose(bd("410.80")).openPrice(bd("411.00"))
                        .highPrice(bd("416.90")).lowPrice(bd("409.50")).volume(21_876_543L).avgVolume(25_000_000L)
                        .marketCap(bd("3080000000000")).peRatio(bd("37.50")).dividendYield(bd("0.73"))
                        .priceChange(bd("4.40")).priceChangePct(bd("1.07")).currency("USD")
                        .lastUpdated(LocalDateTime.now()).build(),

                StockQuote.builder()
                        .symbol("GOOGL").companyName("Alphabet Inc.").exchange("NASDAQ").sector("Technology")
                        .currentPrice(bd("175.85")).previousClose(bd("173.20")).openPrice(bd("173.50"))
                        .highPrice(bd("176.40")).lowPrice(bd("172.80")).volume(18_234_567L).avgVolume(22_000_000L)
                        .marketCap(bd("2180000000000")).peRatio(bd("26.10")).dividendYield(bd("0.00"))
                        .priceChange(bd("2.65")).priceChangePct(bd("1.53")).currency("USD")
                        .lastUpdated(LocalDateTime.now()).build(),

                StockQuote.builder()
                        .symbol("AMZN").companyName("Amazon.com Inc.").exchange("NASDAQ").sector("Consumer Discretionary")
                        .currentPrice(bd("198.70")).previousClose(bd("195.40")).openPrice(bd("195.80"))
                        .highPrice(bd("199.50")).lowPrice(bd("194.90")).volume(29_456_789L).avgVolume(35_000_000L)
                        .marketCap(bd("2100000000000")).peRatio(bd("58.30")).dividendYield(bd("0.00"))
                        .priceChange(bd("3.30")).priceChangePct(bd("1.69")).currency("USD")
                        .lastUpdated(LocalDateTime.now()).build(),

                StockQuote.builder()
                        .symbol("NVDA").companyName("NVIDIA Corporation").exchange("NASDAQ").sector("Technology")
                        .currentPrice(bd("875.40")).previousClose(bd("862.10")).openPrice(bd("865.00"))
                        .highPrice(bd("880.20")).lowPrice(bd("860.50")).volume(41_987_654L).avgVolume(45_000_000L)
                        .marketCap(bd("2160000000000")).peRatio(bd("68.75")).dividendYield(bd("0.03"))
                        .priceChange(bd("13.30")).priceChangePct(bd("1.54")).currency("USD")
                        .lastUpdated(LocalDateTime.now()).build(),

                StockQuote.builder()
                        .symbol("TSLA").companyName("Tesla Inc.").exchange("NASDAQ").sector("Consumer Discretionary")
                        .currentPrice(bd("245.60")).previousClose(bd("252.30")).openPrice(bd("251.00"))
                        .highPrice(bd("253.80")).lowPrice(bd("243.10")).volume(87_654_321L).avgVolume(95_000_000L)
                        .marketCap(bd("782000000000")).peRatio(bd("62.40")).dividendYield(bd("0.00"))
                        .priceChange(bd("-6.70")).priceChangePct(bd("-2.66")).currency("USD")
                        .lastUpdated(LocalDateTime.now()).build(),

                StockQuote.builder()
                        .symbol("JPM").companyName("JPMorgan Chase & Co.").exchange("NYSE").sector("Financials")
                        .currentPrice(bd("198.45")).previousClose(bd("196.80")).openPrice(bd("197.00"))
                        .highPrice(bd("199.20")).lowPrice(bd("196.50")).volume(9_876_543L).avgVolume(11_000_000L)
                        .marketCap(bd("573000000000")).peRatio(bd("11.85")).dividendYield(bd("2.32"))
                        .priceChange(bd("1.65")).priceChangePct(bd("0.84")).currency("USD")
                        .lastUpdated(LocalDateTime.now()).build(),

                StockQuote.builder()
                        .symbol("SPY").companyName("SPDR S&P 500 ETF Trust").exchange("NYSE").sector("ETF")
                        .currentPrice(bd("527.80")).previousClose(bd("524.30")).openPrice(bd("524.50"))
                        .highPrice(bd("528.90")).lowPrice(bd("523.80")).volume(65_432_198L).avgVolume(70_000_000L)
                        .marketCap(bd("490000000000")).peRatio(bd("23.80")).dividendYield(bd("1.28"))
                        .priceChange(bd("3.50")).priceChangePct(bd("0.67")).currency("USD")
                        .lastUpdated(LocalDateTime.now()).build(),

                StockQuote.builder()
                        .symbol("QQQ").companyName("Invesco QQQ Trust").exchange("NASDAQ").sector("ETF")
                        .currentPrice(bd("458.90")).previousClose(bd("454.20")).openPrice(bd("454.50"))
                        .highPrice(bd("460.10")).lowPrice(bd("453.70")).volume(43_876_543L).avgVolume(48_000_000L)
                        .marketCap(bd("218000000000")).peRatio(bd("32.10")).dividendYield(bd("0.58"))
                        .priceChange(bd("4.70")).priceChangePct(bd("1.03")).currency("USD")
                        .lastUpdated(LocalDateTime.now()).build(),

                StockQuote.builder()
                        .symbol("META").companyName("Meta Platforms Inc.").exchange("NASDAQ").sector("Technology")
                        .currentPrice(bd("527.15")).previousClose(bd("520.40")).openPrice(bd("521.00"))
                        .highPrice(bd("528.75")).lowPrice(bd("519.80")).volume(14_567_890L).avgVolume(18_000_000L)
                        .marketCap(bd("1340000000000")).peRatio(bd("28.90")).dividendYield(bd("0.38"))
                        .priceChange(bd("6.75")).priceChangePct(bd("1.30")).currency("USD")
                        .lastUpdated(LocalDateTime.now()).build()
        );

        quoteRepository.saveAll(quotes);
        log.info("Seeded {} stock quotes", quotes.size());
    }

    // -------------------------------------------------------------------------
    // Portfolio Holdings – two accounts
    // -------------------------------------------------------------------------
    private void seedPortfolioHoldings() {
        List<PortfolioHolding> holdings = List.of(
                // --- Account ACC-001 ---
                holding("ACC-001", "AAPL", "Apple Inc.", "EQUITY",
                        "150", "145.20", "189.30"),
                holding("ACC-001", "MSFT", "Microsoft Corporation", "EQUITY",
                        "80", "310.50", "415.20"),
                holding("ACC-001", "NVDA", "NVIDIA Corporation", "EQUITY",
                        "50", "620.00", "875.40"),
                holding("ACC-001", "SPY", "SPDR S&P 500 ETF Trust", "ETF",
                        "200", "480.00", "527.80"),
                holding("ACC-001", "TSLA", "Tesla Inc.", "EQUITY",
                        "40", "220.00", "245.60"),

                // --- Account ACC-002 ---
                holding("ACC-002", "GOOGL", "Alphabet Inc.", "EQUITY",
                        "60", "155.00", "175.85"),
                holding("ACC-002", "AMZN", "Amazon.com Inc.", "EQUITY",
                        "75", "170.00", "198.70"),
                holding("ACC-002", "META", "Meta Platforms Inc.", "EQUITY",
                        "30", "480.00", "527.15"),
                holding("ACC-002", "JPM", "JPMorgan Chase & Co.", "EQUITY",
                        "120", "175.00", "198.45"),
                holding("ACC-002", "QQQ", "Invesco QQQ Trust", "ETF",
                        "100", "400.00", "458.90")
        );

        holdingRepository.saveAll(holdings);
        log.info("Seeded {} portfolio holdings", holdings.size());
    }

    private PortfolioHolding holding(String accountId, String symbol, String company,
                                     String assetClass, String qty, String avgCost, String price) {
        BigDecimal quantity = bd(qty);
        BigDecimal avgCostPrice = bd(avgCost);
        BigDecimal currentPrice = bd(price);
        BigDecimal marketValue = quantity.multiply(currentPrice).setScale(2, java.math.RoundingMode.HALF_UP);
        BigDecimal costBasis = quantity.multiply(avgCostPrice).setScale(2, java.math.RoundingMode.HALF_UP);
        BigDecimal unrealizedGainLoss = marketValue.subtract(costBasis);
        BigDecimal unrealizedGainLossPct = unrealizedGainLoss
                .divide(costBasis, 6, java.math.RoundingMode.HALF_UP)
                .multiply(bd("100"))
                .setScale(4, java.math.RoundingMode.HALF_UP);

        return PortfolioHolding.builder()
                .accountId(accountId).symbol(symbol).companyName(company)
                .assetClass(assetClass).quantity(quantity)
                .avgCostPrice(avgCostPrice).currentPrice(currentPrice)
                .marketValue(marketValue)
                .unrealizedGainLoss(unrealizedGainLoss)
                .unrealizedGainLossPct(unrealizedGainLossPct)
                .currency("USD")
                .createdAt(LocalDateTime.now().minusDays(90))
                .build();
    }

    // -------------------------------------------------------------------------
    // Trade Transactions
    // -------------------------------------------------------------------------
    private void seedTradeTransactions() {
        LocalDateTime now = LocalDateTime.now();

        List<TradeTransaction> transactions = List.of(
                txn("TXN-20240901-000001", "ACC-001", "ORD-20240901-001000",
                        "AAPL", "Apple Inc.", TransactionType.BUY, "50", "178.50",
                        TransactionStatus.SETTLED, now.minusDays(90)),
                txn("TXN-20240910-000002", "ACC-001", "ORD-20240910-001001",
                        "AAPL", "Apple Inc.", TransactionType.BUY, "100", "126.10",
                        TransactionStatus.SETTLED, now.minusDays(81)),
                txn("TXN-20241001-000003", "ACC-001", "ORD-20241001-001002",
                        "MSFT", "Microsoft Corporation", TransactionType.BUY, "80", "310.50",
                        TransactionStatus.SETTLED, now.minusDays(60)),
                txn("TXN-20241015-000004", "ACC-001", "ORD-20241015-001003",
                        "NVDA", "NVIDIA Corporation", TransactionType.BUY, "50", "620.00",
                        TransactionStatus.SETTLED, now.minusDays(46)),
                txn("TXN-20241101-000005", "ACC-001", "ORD-20241101-001004",
                        "SPY", "SPDR S&P 500 ETF Trust", TransactionType.BUY, "200", "480.00",
                        TransactionStatus.SETTLED, now.minusDays(30)),
                txn("TXN-20241115-000006", "ACC-001", "ORD-20241115-001005",
                        "TSLA", "Tesla Inc.", TransactionType.BUY, "40", "220.00",
                        TransactionStatus.SETTLED, now.minusDays(16)),
                txn("TXN-20241201-000007", "ACC-001", null,
                        "AAPL", "Apple Inc.", TransactionType.DIVIDEND, "1", "0.92",
                        TransactionStatus.SETTLED, now.minusDays(1)),

                // ACC-002 transactions
                txn("TXN-20240905-000008", "ACC-002", "ORD-20240905-002000",
                        "GOOGL", "Alphabet Inc.", TransactionType.BUY, "60", "155.00",
                        TransactionStatus.SETTLED, now.minusDays(87)),
                txn("TXN-20240920-000009", "ACC-002", "ORD-20240920-002001",
                        "AMZN", "Amazon.com Inc.", TransactionType.BUY, "75", "170.00",
                        TransactionStatus.SETTLED, now.minusDays(72)),
                txn("TXN-20241010-000010", "ACC-002", "ORD-20241010-002002",
                        "META", "Meta Platforms Inc.", TransactionType.BUY, "30", "480.00",
                        TransactionStatus.SETTLED, now.minusDays(52)),
                txn("TXN-20241025-000011", "ACC-002", "ORD-20241025-002003",
                        "JPM", "JPMorgan Chase & Co.", TransactionType.BUY, "120", "175.00",
                        TransactionStatus.SETTLED, now.minusDays(37)),
                txn("TXN-20241110-000012", "ACC-002", "ORD-20241110-002004",
                        "QQQ", "Invesco QQQ Trust", TransactionType.BUY, "100", "400.00",
                        TransactionStatus.SETTLED, now.minusDays(21)),
                txn("TXN-20241120-000013", "ACC-002", "ORD-20241120-002005",
                        "GOOGL", "Alphabet Inc.", TransactionType.SELL, "10", "168.50",
                        TransactionStatus.SETTLED, now.minusDays(11))
        );

        transactionRepository.saveAll(transactions);
        log.info("Seeded {} trade transactions", transactions.size());
    }

    private TradeTransaction txn(String txnId, String accountId, String orderId,
                                  String symbol, String company,
                                  TransactionType type, String qty, String price,
                                  TransactionStatus status, LocalDateTime date) {
        BigDecimal quantity = bd(qty);
        BigDecimal unitPrice = bd(price);
        BigDecimal commission = type == TransactionType.DIVIDEND ? BigDecimal.ZERO : bd("4.95");
        BigDecimal totalAmount = quantity.multiply(unitPrice).add(commission)
                .setScale(2, java.math.RoundingMode.HALF_UP);

        return TradeTransaction.builder()
                .transactionId(txnId).accountId(accountId).orderId(orderId)
                .symbol(symbol).companyName(company)
                .transactionType(type).quantity(quantity).price(unitPrice)
                .commission(commission).totalAmount(totalAmount)
                .status(status).transactionDate(date)
                .settlementDate(date.toLocalDate().plusDays(2))
                .currency("USD")
                .createdAt(date)
                .build();
    }

    // -------------------------------------------------------------------------
    // Trade Orders – mix of statuses
    // -------------------------------------------------------------------------
    private void seedTradeOrders() {
        LocalDateTime now = LocalDateTime.now();

        List<TradeOrder> orders = List.of(
                // Historical FILLED orders (ACC-001)
                order("ORD-20240901-001000", "ACC-001", "AAPL", OrderType.MARKET, OrderSide.BUY,
                        "50", null, null, "50", "178.50",
                        OrderStatus.FILLED, TimeInForce.DAY, now.minusDays(90)),
                order("ORD-20240910-001001", "ACC-001", "AAPL", OrderType.LIMIT, OrderSide.BUY,
                        "100", "127.00", null, "100", "126.10",
                        OrderStatus.FILLED, TimeInForce.GTC, now.minusDays(81)),
                order("ORD-20241001-001002", "ACC-001", "MSFT", OrderType.MARKET, OrderSide.BUY,
                        "80", null, null, "80", "310.50",
                        OrderStatus.FILLED, TimeInForce.DAY, now.minusDays(60)),
                order("ORD-20241015-001003", "ACC-001", "NVDA", OrderType.LIMIT, OrderSide.BUY,
                        "50", "625.00", null, "50", "620.00",
                        OrderStatus.FILLED, TimeInForce.GTC, now.minusDays(46)),
                order("ORD-20241101-001004", "ACC-001", "SPY", OrderType.MARKET, OrderSide.BUY,
                        "200", null, null, "200", "480.00",
                        OrderStatus.FILLED, TimeInForce.DAY, now.minusDays(30)),
                order("ORD-20241115-001005", "ACC-001", "TSLA", OrderType.MARKET, OrderSide.BUY,
                        "40", null, null, "40", "220.00",
                        OrderStatus.FILLED, TimeInForce.DAY, now.minusDays(16)),

                // Active/Open orders (ACC-001)
                order("ORD-20241201-001006", "ACC-001", "AAPL", OrderType.LIMIT, OrderSide.SELL,
                        "30", "200.00", null, "0", null,
                        OrderStatus.OPEN, TimeInForce.GTC, now.minusDays(1)),
                order("ORD-20241201-001007", "ACC-001", "NVDA", OrderType.STOP_LIMIT, OrderSide.SELL,
                        "10", "830.00", "840.00", "0", null,
                        OrderStatus.OPEN, TimeInForce.GTC, now.minusDays(1)),
                order("ORD-20241202-001008", "ACC-001", "MSFT", OrderType.LIMIT, OrderSide.BUY,
                        "20", "400.00", null, "0", null,
                        OrderStatus.PENDING, TimeInForce.DAY, now.minusHours(2)),

                // Historical FILLED orders (ACC-002)
                order("ORD-20240905-002000", "ACC-002", "GOOGL", OrderType.MARKET, OrderSide.BUY,
                        "60", null, null, "60", "155.00",
                        OrderStatus.FILLED, TimeInForce.DAY, now.minusDays(87)),
                order("ORD-20240920-002001", "ACC-002", "AMZN", OrderType.LIMIT, OrderSide.BUY,
                        "75", "172.00", null, "75", "170.00",
                        OrderStatus.FILLED, TimeInForce.GTC, now.minusDays(72)),
                order("ORD-20241010-002002", "ACC-002", "META", OrderType.MARKET, OrderSide.BUY,
                        "30", null, null, "30", "480.00",
                        OrderStatus.FILLED, TimeInForce.DAY, now.minusDays(52)),
                order("ORD-20241025-002003", "ACC-002", "JPM", OrderType.LIMIT, OrderSide.BUY,
                        "120", "176.00", null, "120", "175.00",
                        OrderStatus.FILLED, TimeInForce.GTC, now.minusDays(37)),
                order("ORD-20241110-002004", "ACC-002", "QQQ", OrderType.MARKET, OrderSide.BUY,
                        "100", null, null, "100", "400.00",
                        OrderStatus.FILLED, TimeInForce.DAY, now.minusDays(21)),
                order("ORD-20241120-002005", "ACC-002", "GOOGL", OrderType.LIMIT, OrderSide.SELL,
                        "10", "168.00", null, "10", "168.50",
                        OrderStatus.FILLED, TimeInForce.GTC, now.minusDays(11)),

                // Open/Pending (ACC-002)
                order("ORD-20241202-002006", "ACC-002", "META", OrderType.LIMIT, OrderSide.BUY,
                        "10", "510.00", null, "0", null,
                        OrderStatus.OPEN, TimeInForce.GTC, now.minusHours(5)),
                order("ORD-20241202-002007", "ACC-002", "AMZN", OrderType.STOP, OrderSide.SELL,
                        "25", null, "188.00", "0", null,
                        OrderStatus.OPEN, TimeInForce.GTC, now.minusHours(3)),

                // Cancelled order
                order("ORD-20241130-001009", "ACC-001", "TSLA", OrderType.LIMIT, OrderSide.BUY,
                        "20", "238.00", null, "0", null,
                        OrderStatus.CANCELLED, TimeInForce.DAY, now.minusDays(2))
        );

        orderRepository.saveAll(orders);
        log.info("Seeded {} trade orders", orders.size());
    }

    private TradeOrder order(String orderId, String accountId, String symbol,
                              OrderType type, OrderSide side,
                              String qty, String limitPrice, String stopPrice,
                              String filledQty, String avgFilledPrice,
                              OrderStatus status, TimeInForce tif, LocalDateTime createdAt) {
        return TradeOrder.builder()
                .orderId(orderId).accountId(accountId).symbol(symbol)
                .orderType(type).side(side)
                .quantity(bd(qty))
                .limitPrice(limitPrice != null ? bd(limitPrice) : null)
                .stopPrice(stopPrice != null ? bd(stopPrice) : null)
                .filledQuantity(bd(filledQty))
                .avgFilledPrice(avgFilledPrice != null ? bd(avgFilledPrice) : null)
                .status(status).timeInForce(tif)
                .createdAt(createdAt)
                .build();
    }

    private BigDecimal bd(String value) {
        return new BigDecimal(value);
    }
}
