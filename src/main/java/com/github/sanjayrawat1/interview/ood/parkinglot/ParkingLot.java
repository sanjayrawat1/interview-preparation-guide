package com.github.sanjayrawat1.interview.ood.parkinglot;

import com.github.sanjayrawat1.interview.ood.parkinglot.fare.FareCalculator;
import com.github.sanjayrawat1.interview.ood.parkinglot.fare.Ticket;
import com.github.sanjayrawat1.interview.ood.parkinglot.spot.ParkingManager;
import com.github.sanjayrawat1.interview.ood.parkinglot.spot.ParkingSpot;
import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author sanjayrawat1
 */
public class ParkingLot {

    private final ParkingManager parkingManager;

    private final FareCalculator fareCalculator;

    public ParkingLot(ParkingManager parkingManager, FareCalculator fareCalculator) {
        this.parkingManager = parkingManager;
        this.fareCalculator = fareCalculator;
    }

    public Ticket enter(Vehicle vehicle) {
        ParkingSpot spot = parkingManager.park(vehicle);
        if (spot != null) {
            // create ticket with entry time
            Ticket ticket = new Ticket(generateTicketId(), vehicle, spot, LocalDateTime.now());
            return ticket;
        } else {
            return null;
        }
    }

    public void exit(Ticket ticket) {
        if (ticket != null && ticket.getExitTime() == null) {
            ticket.setExitTime(LocalDateTime.now());
            parkingManager.unpark(ticket.getVehicle());
            // calculate the fare
            BigDecimal fare = fareCalculator.calculateFare(ticket);
        } else {
            // invalid ticket or vehicle already exited.
        }
    }

    private String generateTicketId() {
        return "TICKET-" + System.currentTimeMillis();
    }
}
