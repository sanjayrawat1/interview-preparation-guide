package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.showing;

import com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location.Room;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * <p>
 * Screening class defines a specific showing of a movie in a particular room at a scheduled time.
 * It combines a Movie, a Room, and time details into a single entity.
 * <p>
 * The Screening class combines a Movie with a specific room, time, and duration, representing a scheduled
 * instance of that movie being played in a particular cinema room.
 * <p>
 * While the Movie class captures attributes intrinsic to the film itself, the Screening class incorporates
 * contextual details about when and where the movie is being presented, making it specific to a time slot
 * and location.
 * <p>
 * The Screening class centralizes scheduling details, making it easier to manage showtimes across cinemas.
 * It ensures a clear separation of concerns and simplifies schedule management.
 *
 * @author sanjayrawat1
 */
public record Screening(
        String id,
        Movie movie,
        Room room,
        LocalDateTime startTime,
        LocalDateTime endTime
) {

    /**
     * To calculate the duration of the screening based on its start and end times.
     */
    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }
}
