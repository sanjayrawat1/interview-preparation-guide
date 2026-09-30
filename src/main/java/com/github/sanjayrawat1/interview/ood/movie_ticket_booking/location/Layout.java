package com.github.sanjayrawat1.interview.ood.movie_ticket_booking.location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * The Layout class acts as a bridge between individual seats and cinema rooms.
 * It organizes seats into a grid structure defined by rows and columns.
 * It uses a nested map (Map<Integer, Map<Integer, Seat>>) for efficient seat lookup,
 * where the outer map’s key is the row number, the inner map’s key is the column number
 * within that row, and the value is the Seat object at that position (row → column → seat).
 *
 * @author sanjayrawat1
 */
public class Layout {

    private final int rows;

    private final int columns;

    // Maps seat numbers (e.g., "0-0") to Seat objects for direct access
    private final Map<String, Seat> seatsByNumber;

    // Nested map for position-based access (row → column → seat)
    private final Map<Integer, Map<Integer, Seat>> seatsByPosition;

    public Layout(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seatsByNumber = new HashMap<>();
        this.seatsByPosition = new HashMap<>();
        initializeLayout();
    }

    // Creates seats for all positions with default null pricing
    private void initializeLayout() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                String seatNumber = r + "-" + c;
                addSeat(seatNumber, r, c, new Seat(seatNumber, null));
            }
        }
    }

    public void addSeat(String seatNumber, int row, int column, Seat seat) {
        // Store seat in number-based lookup map
        seatsByNumber.put(seatNumber, seat);

        // Store seat in position-based lookup map
        seatsByPosition
                .computeIfAbsent(row, k -> new HashMap<>())
                .put(column, seat);
    }

    public Seat getSeatByNumber(String seatNumber) {
        return seatsByNumber.get(seatNumber);
    }

    // Gets a seat by its row and column position
    public Seat getSeatByPosition(int row, int column) {
        Map<Integer, Seat> rowSeats = seatsByPosition.get(row);
        return (rowSeats != null) ? rowSeats.get(column) : null;
    }

    public List<Seat> getAllSeats() {
        return List.copyOf(seatsByNumber.values());
    }
}
