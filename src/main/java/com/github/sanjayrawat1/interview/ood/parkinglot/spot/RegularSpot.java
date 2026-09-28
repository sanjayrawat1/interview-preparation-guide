package com.github.sanjayrawat1.interview.ood.parkinglot.spot;

import com.github.sanjayrawat1.interview.ood.parkinglot.vehicle.VehicleSize;

/**
 *
 * @author sanjayrawat1
 */
public class RegularSpot extends AbstractParkingSpot {

    public RegularSpot(int spotNumber) {
        super(spotNumber);
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }
}
