package com.github.sanjayrawat1.interview.ood.parkinglot;

import com.github.sanjayrawat1.interview.ood.parkinglot.fare.BaseFareStrategy;
import com.github.sanjayrawat1.interview.ood.parkinglot.fare.FareCalculator;
import com.github.sanjayrawat1.interview.ood.parkinglot.fare.FareStrategy;
import com.github.sanjayrawat1.interview.ood.parkinglot.fare.PeakHourFareStrategy;
import com.github.sanjayrawat1.interview.ood.parkinglot.fare.Ticket;
import com.github.sanjayrawat1.interview.ood.parkinglot.spot.ParkingManager;
import com.github.sanjayrawat1.interview.ood.parkinglot.spot.ParkingSpot;
import com.github.sanjayrawat1.interview.ood.parkinglot.spot.RegularSpot;
import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.Car;
import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.Vehicle;
import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.VehicleSize;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author sanjayrawat1
 */
class ParkingLotTest {

    @Test
    public void testVehicleJourney() {
        System.out.println("=== Testing parking lot system: Complete Vehicle Journey ===");
        System.out.println("--- Setting up parking spots ---");
        Map<VehicleSize, List<ParkingSpot>> availableSpots = new HashMap<>();
        availableSpots.put(VehicleSize.MEDIUM, new ArrayList<>());
        availableSpots.get(VehicleSize.MEDIUM).add(new RegularSpot(1));
        availableSpots.get(VehicleSize.MEDIUM).add(new RegularSpot(2));

        System.out.println("✓ Created 2 regular parking spots for medium-sized vehicles");
        System.out.println("  - Spot 1: Regular parking spot");
        System.out.println("  - Spot 2: Regular parking spot");

        System.out.println("--- Initializing Parking Manager ---");
        ParkingManager parkingManager = new ParkingManager(availableSpots);
        System.out.println("✓ Parking manager initialized with available spots");

        System.out.println("--- Setting Up Fare Calculation System ---");
        List<FareStrategy> strategies = new ArrayList<>(List.of(new BaseFareStrategy(), new PeakHourFareStrategy()));
        FareCalculator fareCalculator = new FareCalculator(strategies);
        System.out.println("✓ Fare calculator initialized with multiple strategies:");
        System.out.println("  - Base fare strategy");
        System.out.println("  - Peak hours fare strategy");

        ParkingLot parkingLot = new ParkingLot(parkingManager, fareCalculator);

        System.out.println("--- Creating Test Vehicle ---");
        Vehicle car = new Car("ABC123");
        System.out.println("✓ Created car with license plate: ABC123");
        System.out.println("  - Vehicle type: Car (MEDIUM size)");

        System.out.println("--- Vehicle Entering Parking Lot ---");
        // Vehicle enters the parking lot
        Ticket ticket = parkingLot.enter(car);
        System.out.println("✓ Ticket generated for vehicle ABC123");
        System.out.println("✓ Parking spot assigned: " + ticket.getParkingSpot().getSpotNumber());
        assertNotNull(ticket, "Ticket should not be null");
        assertEquals(car, ticket.getVehicle(), "Vehicle should match the one that entered");
        assertNotNull(ticket.getParkingSpot(), "Parking spot should not be null");
        System.out.println("✓ Ticket validation passed:");
        System.out.println("  - Ticket is not null");
        System.out.println("  - Vehicle matches the one that entered");
        System.out.println("  - Parking spot assigned successfully");

        // Find the vehicle in the parking lot
        ParkingSpot foundSpot = parkingManager.findSpotByVehicle(car);
        assertNotNull(foundSpot, "Vehicle should be found in the parking lot");
        assertEquals(ticket.getParkingSpot(), foundSpot, "Found spot should match the ticket's spot");

        System.out.println("--- Vehicle Leaving Parking Lot ---");
        // Vehicle leaves the parking lot
        parkingLot.exit(ticket);
        assertNotNull(ticket.getExitTime(), "Exit time should be set");
        assertTrue(foundSpot.isAvailable(), "Parking spot should be available after vehicle leaves");
        System.out.println("✓ Vehicle exit verification passed:");
        System.out.println("  - Exit time recorded on ticket");
        System.out.println("  - Parking spot is now available for other vehicles");
        System.out.println("=== Parking Lot Vehicle Journey Test Completed Successfully ===");
    }
}