package com.github.sanjayrawat1.interview.ood.parkinglot.vehicle;

/**
 * <p>
 * This object represents a vehicle that needs a spot.
 *
 * @author sanjayrawat1
 */
public interface Vehicle {

    String getIdentificationNumber();

    VehicleSize getSize();
}
