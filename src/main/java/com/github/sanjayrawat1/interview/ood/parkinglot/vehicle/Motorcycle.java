package com.github.sanjayrawat1.interview.ood.parkinglot.vehicle;

/**
 *
 * @author sanjayrawat1
 */
public class Motorcycle extends RegisteredVehicle {

    public Motorcycle(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }
}
