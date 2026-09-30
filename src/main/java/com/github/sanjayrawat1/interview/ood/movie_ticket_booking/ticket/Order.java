package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * The Order class represents a single transaction in the movie ticket booking system. It encapsulates a
 * collection of Ticket objects purchased together and records the date and time of the order (orderDate).
 * This design ensures that all tickets associated with a single booking are grouped and tracked cohesively.
 *
 * @author sanjayrawat1
 */
public class Order {

    private final List<Ticket> tickets;

    private final LocalDateTime orderDate;

    public Order(LocalDateTime orderDate) {
        this.tickets = new ArrayList<>();
        this.orderDate = orderDate;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public BigDecimal calculateTotalPrice() {
        return tickets.stream()
                .map(Ticket::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Ticket> getAllTickets() {
        return List.copyOf(tickets);
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
