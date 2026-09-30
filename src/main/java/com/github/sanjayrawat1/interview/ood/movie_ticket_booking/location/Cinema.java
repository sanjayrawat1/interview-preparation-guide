package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sanjayrawat1
 */
public class Cinema {

    private final String name;

    private final String location;

    private final List<Room> rooms;

    public Cinema(String name, String location) {
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
