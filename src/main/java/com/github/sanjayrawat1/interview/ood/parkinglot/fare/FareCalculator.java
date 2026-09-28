package com.github.sanjayrawat1.interview.ood.parkinglot.fare;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author sanjayrawat1
 */
public class FareCalculator {

    /**
     * We choose List over an array or Set because it preserves order. Strategies like BaseFareStrategy
     * must be applied before PeakHoursFareStrategy for correct fare calculation. A Set can prevent
     * duplicates but loses order, while an array maintains a fixed size, limiting flexibility.
     */
    private final List<FareStrategy> strategies;

    public FareCalculator(List<FareStrategy> strategies) {
        this.strategies = strategies;
    }

    public BigDecimal calculateFare(Ticket ticket) {
        BigDecimal fare = BigDecimal.ZERO;
        for (FareStrategy strategy : strategies) {
            fare = strategy.calculateFare(ticket, fare);
        }
        return fare;
    }
}
