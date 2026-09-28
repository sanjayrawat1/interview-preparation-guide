package com.github.sanjayrawat1.interview.ood.parkinglot.fare;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * Since a parking session often involves multiple pricing rules, like duration, size, and time, we design a FareCalculator class
 * to coordinate these changes and calculate the final fee. It is designed to determine the cost for each ticket by combining the
 * effects of all applicable strategies (BaseFareStrategy, PeakHoursFareStrategy), ensuring the system applies the right fee
 * based on how long the vehicle stays, its size, and when it is parked.
 *
 * @author sanjayrawat1
 */
public class FareCalculator {

    /**
     * <p>
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
