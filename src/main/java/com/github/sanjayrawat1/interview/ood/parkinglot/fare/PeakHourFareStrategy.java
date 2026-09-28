package com.github.sanjayrawat1.interview.ood.parkinglot.fare;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author sanjayrawat1
 */
public class PeakHourFareStrategy implements FareStrategy {

    // 50% higher during peak hours
    private final BigDecimal PEAK_HOURS_MULTIPLIER = new BigDecimal("1.5");

    /**
     * Multiplies the input fare by 1.5 if the entry time falls within peak hours.
     * Otherwise, it leaves it unchanged.
     * Adjusts the fare for high-demand periods, increasing costs during busy times.
     */
    @Override
    public BigDecimal calculateFare(Ticket ticket, BigDecimal inputFare) {
        BigDecimal fare = inputFare;
        if (isPeakHours(ticket.getEntryTime())) {
            fare = fare.multiply(PEAK_HOURS_MULTIPLIER);
        }
        return fare;
    }

    private boolean isPeakHours(LocalDateTime time) {
        int hour = time.getHour();
        return (hour >= 7 && hour <= 10) || (hour >= 16 && hour <= 19);
    }
}
