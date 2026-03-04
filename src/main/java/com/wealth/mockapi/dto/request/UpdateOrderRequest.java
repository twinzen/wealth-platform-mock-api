package com.wealth.mockapi.dto.request;

import com.wealth.mockapi.model.enums.TimeInForce;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateOrderRequest {

    /** New quantity – null means no change */
    @Positive(message = "Quantity must be positive")
    private BigDecimal quantity;

    /** New limit price – null means no change */
    @Positive(message = "Limit price must be positive")
    private BigDecimal limitPrice;

    /** New stop price – null means no change */
    @Positive(message = "Stop price must be positive")
    private BigDecimal stopPrice;

    private TimeInForce timeInForce;

    @Size(max = 500, message = "Notes must not exceed 500 characters")
    private String notes;
}
