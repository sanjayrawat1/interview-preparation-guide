package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.ticket;

import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location.Seat;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.showing.Screening;

import java.math.BigDecimal;

/**
 * <p>
 * The Ticket class represents a single unit of purchase (a seat) in the ticket booking system. It connects a Screening
 * (scheduled movie instance) with a specific Seat and assigns a price at the time of booking. This design ensures
 * that the ticket encapsulates all necessary details for a single booking, making it central to the ticketing process.
 *
 * @author sanjayrawat1
 */
public record Ticket(
        Screening screening,
        Seat seat,
        BigDecimal price
) {

}
