package com.github.sanjayrawat1.interview.ood.parkinglot.spot;

import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.Vehicle;

/**
 *
 * @author sanjayrawat1
 */
public abstract class AbstractParkingSpot implements ParkingSpot {

    protected final int spotNumber;

    protected Vehicle vehicle;

    public AbstractParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.vehicle = null;
    }

    @Override
    public int getSpotNumber() {
        return spotNumber;
    }

    @Override
    public boolean isAvailable() {
        return vehicle == null;
    }

    @Override
    public void occupy(Vehicle vehicle) {
        if (isAvailable()) {
            this.vehicle = vehicle;
        }
    }

    @Override
    public void vacate() {
        this.vehicle = null;
    }
}
