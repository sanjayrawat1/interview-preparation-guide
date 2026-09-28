package com.github.sanjayrawat1.interview.ood.parkinglot.spot;

import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.Vehicle;
import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.VehicleSize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * This object oversees the parking lot’s spot allocation, managing the assignment, lookup, and release of ParkingSpot instances.
 * It ensures a Vehicle gets the right spot by checking availability based on size, and updates the system when vehicles leave,
 * keeping parking operations smooth and efficient.
 *
 * @author sanjayrawat1
 */
public class ParkingManager {

    private final Map<VehicleSize, List<ParkingSpot>> availableSpots;

    private final Map<Vehicle, ParkingSpot> vehicleToParkingSpot;

    private final Map<ParkingSpot, Vehicle> parkingSpotToVehicle;

    public ParkingManager(Map<VehicleSize, List<ParkingSpot>> availableSpots) {
        this.availableSpots = availableSpots;
        this.vehicleToParkingSpot = new HashMap<>();
        this.parkingSpotToVehicle = new HashMap<>();
    }

    public ParkingSpot findSpot(Vehicle vehicle) {
        VehicleSize vehicleSize = vehicle.getSize();
        // Start looking for the smallest spot that can fit the vehicle
        for (VehicleSize size : VehicleSize.values()) {
            if (size.ordinal() >= vehicleSize.ordinal()) {
                List<ParkingSpot> spots = availableSpots.get(size);
                for (ParkingSpot spot : spots) {
                    if (spot.isAvailable()) {
                        return spot; // Return the first available spot
                    }
                }
            }
        }
        return null;
    }

    public ParkingSpot park(Vehicle vehicle) {
        ParkingSpot spot = findSpot(vehicle);
        if (spot == null) {
            return null;
        }

        spot.occupy(vehicle);
        vehicleToParkingSpot.put(vehicle, spot);
        parkingSpotToVehicle.put(spot, vehicle);
        availableSpots.get(spot.getSize()).remove(spot);
        return spot;
    }

    public void unpark(Vehicle vehicle) {
        ParkingSpot spot = vehicleToParkingSpot.remove(vehicle);
        if (spot == null) {
            return;
        }

        spot.vacate();
        parkingSpotToVehicle.remove(spot);
        availableSpots.get(vehicle.getSize()).add(spot);
    }

    public ParkingSpot findSpotByVehicle(Vehicle vehicle) {
        return vehicleToParkingSpot.get(vehicle);
    }

    public Vehicle findVehicleBySpot(ParkingSpot spot) {
        return parkingSpotToVehicle.get(spot);
    }
}
