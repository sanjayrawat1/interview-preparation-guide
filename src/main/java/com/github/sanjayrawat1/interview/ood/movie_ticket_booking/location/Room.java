package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location;

/**
 * <p>
 * The Room class represents a theater room within a cinema, with attributes such as its unique room number
 * and a Layout object that defines its seating arrangement.
 *
 * @author sanjayrawat1
 */
public class Room {

    private final String roomNumber;

    private final Layout layout;

    public Room(String roomNumber, Layout layout) {
        this.roomNumber = roomNumber;
        this.layout = layout;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Layout getLayout() {
        return layout;
    }
}
