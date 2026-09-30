package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.handling_concurrent_bookings.optimistic_locking;

import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location.Seat;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.showing.Movie;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.showing.Screening;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.ticket.Ticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * The ScreeningManager class serves as a centralized manager for showtimes and tickets within the movie
 * ticket booking system. It maintains mappings between Movie objects and their corresponding Screening
 * instances, as well as between Screening objects and their associated Ticket objects. This design ensures
 * that the system can dynamically manage showtimes and ticket bookings while maintaining clear relationships
 * between components.
 *
 * @author sanjayrawat1
 */
public class ScreeningManager {

    // Maps movies to their scheduled screenings
    private final Map<Movie, List<Screening>> screeningsByMovie;

    // Maps screenings to tickets sold for that screening
    private final Map<Screening, List<Ticket>> ticketsByScreening;

    public ScreeningManager() {
        this.screeningsByMovie = new HashMap<>();
        this.ticketsByScreening = new HashMap<>();
    }

    public void addScreening(Movie movie, Screening screening) {
        screeningsByMovie
                .computeIfAbsent(movie, k -> new ArrayList<>())
                .add(screening);
    }

    public List<Screening> getScreeningsForMovie(Movie movie) {
        return screeningsByMovie.getOrDefault(movie, new ArrayList<>());
    }

    public void addTicket(Screening screening, Ticket ticket) {
        ticketsByScreening
                .computeIfAbsent(screening, k -> new ArrayList<>())
                .add(ticket);
    }

    public List<Ticket> getTicketsForScreening(Screening screening) {
        return ticketsByScreening.getOrDefault(screening, new ArrayList<>());
    }

    /**
     * Determines which seats are available for a given screening. It retrieves all seats from the room's
     * Layout and removes seats that are already associated with tickets for the screening. This logic
     * ensures accurate seat availability by dynamically reflecting the current booking status.
     */
    public List<Seat> getAvailableSeats(Screening screening) {
        List<Seat> allSeats = screening.room().getLayout().getAllSeats();
        List<Ticket> bookedTickets = getTicketsForScreening(screening);

        List<Seat> availableSeats = new ArrayList<>(allSeats);
        for (Ticket ticket : bookedTickets) {
            availableSeats.remove(ticket.seat());
        }
        return availableSeats;
    }

    public synchronized Ticket bookSeatOptimistically(Screening screening, Seat seat) {
        // First check if a seat is available (optimistic)
        if (isSeatBooked(screening, seat)) {
            throw new IllegalStateException("Seat is already booked");
        }

        // Create ticket - at this point, we're optimistically assuming
        // the seat is still available
        BigDecimal price = seat.getPricingStrategy().getPrice();
        Ticket ticket = new Ticket(screening, seat, price);

        // Add to booking system - this effectively "reserves" the seat
        ticketsByScreening
                .computeIfAbsent(screening, k -> new ArrayList<>())
                .add(ticket);

        return ticket;
    }

    public boolean isSeatBooked(Screening screening, Seat seat) {
        List<Ticket> tickets = getTicketsForScreening(screening);
        return tickets
                .stream()
                .anyMatch(ticket -> ticket.seat().equals(seat));
    }
}
