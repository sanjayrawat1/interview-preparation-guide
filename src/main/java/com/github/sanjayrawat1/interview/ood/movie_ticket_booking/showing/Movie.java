package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.showing;

import java.time.Duration;

/**
 * <p>
 * Represents a specific movie shown in cinemas.
 * The Movie class represents the static details of a film, such as its title, genre, and duration, which remain
 * consistent across all screenings. The class is designed to be immutable, as it does not include setter methods,
 * ensuring that movie details cannot be altered once the object is created. This immutability guarantees data
 * integrity and reflects the static nature of a movie’s attributes.
 *
 * @author sanjayrawat1
 */
public record Movie(
        String title,
        String genre,
        int durationInMinutes
) {

    public Duration getDuration() {
        return Duration.ofMinutes(durationInMinutes);
    }
}
