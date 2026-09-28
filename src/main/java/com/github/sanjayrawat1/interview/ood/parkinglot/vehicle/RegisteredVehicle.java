package com.github.sanjayrawat1.interview.ood.parkinglot.vehicle;

/**
 *
 * @author sanjayrawat1
 */
public abstract class RegisteredVehicle implements Vehicle {
    
    protected String licensePlate;
    
    public RegisteredVehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    public String getIdentificationNumber() {
        return licensePlate;
    }
}
