package main.models;

import java.math.BigDecimal;

public class Price {
    private final BigDecimal amount;

    public Price(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }
}
