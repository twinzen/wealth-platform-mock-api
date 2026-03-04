package com.wealth.mockapi.dto.request;

import com.wealth.mockapi.model.enums.OrderSide;
import com.wealth.mockapi.model.enums.OrderType;
import com.wealth.mockapi.model.enums.TimeInForce;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlaceOrderRequest {

    @NotBlank(message = "Account ID is required")
    private String accountId;

    @NotBlank(message = "Symbol is required")
    @Size(max = 20, message = "Symbol must not exceed 20 characters")
    private String symbol;

    @NotNull(message = "Order type is required")
    private OrderType orderType;

    @NotNull(message = "Order side is required")
    private OrderSide side;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private BigDecimal quantity;

    /** Required for LIMIT and STOP_LIMIT orders */
    @Positive(message = "Limit price must be positive")
    private BigDecimal limitPrice;

    /** Required for STOP and STOP_LIMIT orders */
    @Positive(message = "Stop price must be positive")
    private BigDecimal stopPrice;

    private TimeInForce timeInForce = TimeInForce.DAY;

    @Size(max = 500, message = "Notes must not exceed 500 characters")
    private String notes;
}
