package com.github.sanjayrawat1.interview.ood.parkinglot.spot;

import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.Vehicle;
import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.VehicleSize;

/**
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
