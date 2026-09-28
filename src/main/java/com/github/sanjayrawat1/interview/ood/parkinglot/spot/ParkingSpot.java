package com.github.sanjayrawat1.interview.ood.parkinglot.spot;

import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.Vehicle;
import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.VehicleSize;

/**
 * <p>
 * This object models an individual parking spot in the parking lot. It’s the physical space where a Vehicle parks,
 * ensuring only appropriately sized vehicles can park based on its capacity.
 *
 * @author sanjayrawat1
 */
public interface ParkingSpot {

    int getSpotNumber();

    boolean isAvailable();

    void occupy(Vehicle vehicle);

    void vacate();

    VehicleSize getSize();
}
