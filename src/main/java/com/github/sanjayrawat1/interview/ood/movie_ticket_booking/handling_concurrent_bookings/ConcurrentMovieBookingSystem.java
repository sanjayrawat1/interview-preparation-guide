package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.handling_concurrent_bookings;

import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.handling_concurrent_bookings.pessimistic_locking.SeatLockManager;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location.Cinema;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location.Seat;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.showing.Movie;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.showing.Screening;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.handling_concurrent_bookings.optimistic_locking.ScreeningManager;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.ticket.Ticket;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * The MovieBookingSystem class serves as a facade for the movie ticket booking system, providing a
 * simplified interface for managing the core data and operations. It abstracts away the complexities
 * of interacting with underlying components, such as ScreeningManager, while maintaining seamless
 * integration with Movie, Cinema, Screening, and Seat.
 * <p>
 * Key functionalities include adding movies and cinemas, retrieving available seats for a specific
 * screening, finding screenings for a given movie, and booking tickets.
 *
 * @author sanjayrawat1
 */
public class ConcurrentMovieBookingSystem {

    private final List<Movie> movies;

    private final List<Cinema> cinemas;

    private final ScreeningManager screeningManager;

    private final SeatLockManager seatLockManager;

    public ConcurrentMovieBookingSystem() {
        this.movies = new ArrayList<>();
        this.cinemas = new ArrayList<>();
        this.screeningManager = new ScreeningManager();
        this.seatLockManager = new SeatLockManager(Duration.ofMinutes(5));
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public void addScreening(Movie movie, Screening screening) {
        screeningManager.addScreening(movie, screening);
    }

    /**
     * Attempts to temporarily lock a specific seat for a given screening for a user.
     * This is the first step in a pessimistic booking flow.
     *
     * @param screening The screening for which the seat is to be locked.
     * @param seatNumber The seat number (e.g., "A-10") to lock.
     * @param userId The ID of the user attempting to lock the seat.
     * @return true if the seat was successfully locked, false otherwise (e.g., already locked or booked).
     */
    public boolean selectAndLockSeat(Screening screening, String seatNumber, String userId) {
        // First, check if the seat exists in the screening's room layout
        Seat seat = screening.room().getLayout().getSeatByNumber(seatNumber);
        if (seat == null) {
            return false;
        }

        // Second, ensure the seat is not already booked (permanently)
        if (screeningManager.isSeatBooked(screening, seat)) {
            return false;
        }

        // Third, attempt to acquire a temporary lock
        return seatLockManager.lockSeat(screening, seat, userId);
    }

    /**
     * Books a ticket for a specific seat at a screening, assuming it's already locked by the same user.
     * This is the final step after a successful lock.
     *
     * @param screening The screening for which the ticket is to be booked.
     * @param seatNumber The seat number to book.
     * @param userId The ID of the user who previously locked the seat.
     * @return The created Ticket if successful, Optional.empty() if not (e.g., lock expired, wrong user).
     */
    public Optional<Ticket> bookLockedTicket(Screening screening, String seatNumber, String userId) {
        Seat seat = screening.room().getLayout().getSeatByNumber(seatNumber);
        if (seat == null) {
            return Optional.empty();
        }

        // Verify the lock: It must be locked, not expired, and by the *same* user
        SeatLockManager.SeatLock currentLock = seatLockManager.getLock(screening, seat);
        if (currentLock == null || currentLock.isExpired()) {
            seatLockManager.unlockSeat(screening, seat, userId);
            return Optional.empty();
        }

        // Proceed with booking (this is where the old 'bookTicket' logic goes)
        BigDecimal price = seat.getPricingStrategy().getPrice();
        Ticket ticket = new Ticket(screening, seat, price);
        screeningManager.addTicket(screening, ticket);

        // Successfully booked, now release the temporary lock
        seatLockManager.unlockSeat(screening, seat, userId);

        return Optional.of(ticket);
    }

    /**
     * Allows a user to release a temporary lock on a seat if they decide not to book.
     *
     * @param screening The screening.
     * @param seatNumber The seat number to unlock.
     * @param userId The ID of the user who locked the seat.
     * @return true if the lock was successfully released, false otherwise.
     */
    public boolean releaseSeatLock(Screening screening, String seatNumber, String userId) {
        Seat seat = screening.room().getLayout().getSeatByNumber(seatNumber);
        if (seat == null) {
            return false;
        }

        return seatLockManager.unlockSeat(screening, seat, userId);
    }

    public List<Screening> getScreeningsForMovie(Movie movie) {
        return screeningManager.getScreeningsForMovie(movie);
    }

    public List<Seat> getAvailableSeats(Screening screening) {
        return screeningManager.getAvailableSeats(screening);
    }

    public List<Ticket> getTicketsForScreening(Screening screening) {
        return screeningManager.getTicketsForScreening(screening);
    }

    public int getTicketCount(Screening screening) {
        return screeningManager.getTicketsForScreening(screening).size();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }
}
