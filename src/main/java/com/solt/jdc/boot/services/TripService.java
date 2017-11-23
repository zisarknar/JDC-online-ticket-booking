package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.Trip;

import java.util.List;

public interface TripService {

    List<Trip> getAllTrips();
    Trip getTrip(int id);
}
