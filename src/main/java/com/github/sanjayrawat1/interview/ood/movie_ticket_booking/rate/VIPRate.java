package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.rate;

import java.math.BigDecimal;

/**
 *
 * @author sanjayrawat1
 */
public class VIPRate implements PricingStrategy {

    private final BigDecimal price;

    public VIPRate(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
