package com.github.sanjayrawat1.interview.ood.movie_ticket_booking;

import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location.Cinema;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location.Seat;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.showing.Movie;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.showing.Screening;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.ticket.ScreeningManager;
import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.ticket.Ticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class MovieBookingSystem {

    private final List<Movie> movies;

    private final List<Cinema> cinemas;

    private final ScreeningManager screeningManager;

    public MovieBookingSystem() {
        this.movies = new ArrayList<>();
        this.cinemas = new ArrayList<>();
        this.screeningManager = new ScreeningManager();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public List<Cinema> getAllCinemas() {
        return cinemas;
    }

    public void addScreening(Movie movie, Screening screening) {
        screeningManager.addScreening(movie, screening);
    }

    public void bookTicket(Screening screening, Seat seat) {
        BigDecimal price = seat.getPricingStrategy().getPrice();
        Ticket ticket = new Ticket(screening, seat, price);
        screeningManager.addTicket(screening, ticket);
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
