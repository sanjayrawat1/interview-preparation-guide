package com.github.sanjayrawat1.interview.ood.parkinglot.vehicle;

/**
 *
 * @author sanjayrawat1
 */
public class Car extends RegisteredVehicle {

    public Car(String licensePlate) {
        super(licensePlate);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }
}
