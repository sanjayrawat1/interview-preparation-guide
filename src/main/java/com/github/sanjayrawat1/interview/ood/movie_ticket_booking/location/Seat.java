package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location;

import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.rate.PricingStrategy;

/**
 * <p>
 * The Seat class holds key details about an individual seat, including its unique number.
 * It uses the strategy pattern, implemented through the PricingStrategy.
 * <p>
 * The strategy pattern benefits the system in two key ways:
 * <ul>
 *     <li>It promotes extensibility, making it easy to add new rate classes.</li>
 *     <li>It reduces code redundancy by using a single Seat class for all pricing variations.</li>
 * </ul>
 * <p>
 * An alternative could be to store the price info directly in Seat, with an enum for seat type (e.g., NORMAL, PREMIUM).
 * However, this would embed pricing logic in Seat, making it harder to modify and extend pricing rules without changing the class.
 * <p>
 * By associating a PricingStrategy with each seat, the system achieves flexibility and extensibility in pricing,
 * adhering to the Open-Closed Principle: new pricing strategies can be added without modifying existing code.
 *
 * @author sanjayrawat1
 */
public class Seat {

    private final String seatNumber;

    private PricingStrategy pricingStrategy;

    public Seat(String seatNumber, PricingStrategy pricingStrategy) {
        this.seatNumber = seatNumber;
        this.pricingStrategy = pricingStrategy;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
}
