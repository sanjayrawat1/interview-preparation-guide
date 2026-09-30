package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.rate;

import java.math.BigDecimal;

/**
 *
 * @author sanjayrawat1
 */
public interface PricingStrategy {

    BigDecimal getPrice();
}
