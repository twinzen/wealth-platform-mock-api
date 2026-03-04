# Wealth Platform Mock API

A Spring Boot mock REST API simulating a wealth management platform backend. Provides realistic seed data for portfolios, stock quotes, trade transactions, and trade orders — useful for frontend development, API integration testing, and demos.

## Tech Stack

- **Java 17** + **Spring Boot 3.4.1**
- **H2** in-memory database (auto-reset on restart)
- **Spring Data JPA** + **Hibernate**
- **Lombok**, **Bean Validation**
- **SpringDoc / Swagger UI** (OpenAPI 3)
- **Spring Boot Actuator**

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+

### Run

```bash
mvn spring-boot:run
```

The server starts on **http://localhost:8080**.

### Build

```bash
mvn clean package
java -jar target/wealth-platform-mock-api-1.0.0-SNAPSHOT.jar
```

## Seed Data

Automatically loaded on startup — resets each restart.

| Resource | Count | Details |
|---|---|---|
| Stock Quotes | 10 | AAPL, MSFT, GOOGL, AMZN, NVDA, TSLA, JPM, SPY, QQQ, META |
| Portfolio Holdings | 10 | 5 holdings each for ACC-001 and ACC-002 |
| Trade Transactions | 13 | Mix of BUY, SELL, DIVIDEND — all SETTLED |
| Trade Orders | 18 | Mix of FILLED, OPEN, PENDING, CANCELLED statuses |

**Test accounts:** `ACC-001`, `ACC-002`

## API Reference

All endpoints are prefixed with `/api/v1`.

### Stock Quotes

| Method | Path | Description |
|---|---|---|
| `GET` | `/quotes` | All quotes (optional `?symbols=AAPL,MSFT`) |
| `GET` | `/quotes/{symbol}` | Single quote by symbol |

### Portfolio Holdings

| Method | Path | Description |
|---|---|---|
| `GET` | `/portfolios/{accountId}/holdings` | All holdings for an account |
| `GET` | `/portfolios/{accountId}/holdings/{symbol}` | Single holding |

### Trade Transactions

| Method | Path | Description |
|---|---|---|
| `GET` | `/accounts/{accountId}/transactions` | Paginated transactions |
| `GET` | `/accounts/{accountId}/transactions/{txnId}` | Single transaction |
| `GET` | `/accounts/{accountId}/transactions/symbol/{symbol}` | Transactions by symbol |

### Trade Orders

| Method | Path | Description |
|---|---|---|
| `POST` | `/orders` | Place a new order |
| `GET` | `/orders/{orderId}` | Order status |
| `GET` | `/orders` | Orders by account (`?accountId=X`) with optional `?status=Y` filter |
| `PATCH` | `/orders/{orderId}` | Update an order |
| `DELETE` | `/orders/{orderId}` | Cancel an order |

### Place Order Request Body

```json
{
  "accountId": "ACC-001",
  "symbol": "AAPL",
  "orderType": "LIMIT",
  "side": "BUY",
  "quantity": 10,
  "limitPrice": 185.00,
  "stopPrice": null,
  "timeInForce": "DAY",
  "notes": "Optional notes"
}
```

**Order Types:** `MARKET`, `LIMIT`, `STOP`, `STOP_LIMIT`
**Order Sides:** `BUY`, `SELL`
**Time in Force:** `DAY`, `GTC`, `IOC`, `FOK`
**Order Statuses:** `PENDING`, `OPEN`, `FILLED`, `PARTIALLY_FILLED`, `CANCELLED`, `REJECTED`, `EXPIRED`

## Developer Tools

| Tool | URL |
|---|---|
| Swagger UI | http://localhost:8080/swagger-ui.html |
| OpenAPI JSON | http://localhost:8080/api-docs |
| H2 Console | http://localhost:8080/h2-console |
| Health Check | http://localhost:8080/actuator/health |

**H2 Console settings:**
- JDBC URL: `jdbc:h2:mem:wealthdb`
- Username: `sa`
- Password: *(empty)*

## Static UI Pages

| Page | URL | Description |
|---|---|---|
| Advisor Dashboard | http://localhost:8080/advisor-dashboard.html | Demo wealth advisor UI |
| API Tester | http://localhost:8080/api-tester.html | Interactive API playground |

## Project Structure

```
src/main/java/com/wealth/mockapi/
├── config/         # DataInitializer, OpenApiConfig
├── controller/     # REST controllers (Portfolio, Quote, Transaction, Order)
├── dto/
│   ├── request/    # PlaceOrderRequest, UpdateOrderRequest
│   └── response/   # ApiResponse<T>, PagedResponse<T>
├── exception/      # GlobalExceptionHandler, custom exceptions
├── model/          # JPA entities + enums
├── repository/     # Spring Data JPA repositories
└── service/        # Business logic services
```
