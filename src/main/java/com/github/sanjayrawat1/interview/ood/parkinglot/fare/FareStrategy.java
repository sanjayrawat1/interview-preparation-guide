package com.github.sanjayrawat1.interview.ood.parkinglot.fare;

import java.math.BigDecimal;

/**
 * <p>
 * We design the FareStrategy interface to establish a standard method for modifying the parking fee,
 * allowing various pricing rules to fit into the system.
 *
 * @author sanjayrawat1
 */
public interface FareStrategy {

    BigDecimal calculateFare(Ticket ticket, BigDecimal inputFare);
}
