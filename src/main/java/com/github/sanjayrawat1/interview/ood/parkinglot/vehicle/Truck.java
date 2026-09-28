package com.github.sanjayrawat1.interview.ood.parkinglot.vehicle;

/**
 *
 * @author sanjayrawat1
 */
public class Truck extends RegisteredVehicle {

    public Truck(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }
}
