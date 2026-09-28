package com.github.sanjayrawat1.interview.ood.parkinglot.fare;

import java.math.BigDecimal;

/**
 *
 * @author sanjayrawat1
 */
public interface FareStrategy {

    BigDecimal calculateFare(Ticket ticket, BigDecimal inputFare);
}
