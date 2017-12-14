package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.Trip;

import java.util.Date;
import java.util.List;

public interface TripService {

    List<Trip> getAllTrips();

    Trip getTrip(int id);

    void saveTrip(Trip trip);

    void deleteTrip(int id);

    Trip updateTrip(Trip trip);
    
    List<Trip> findTripByFilter(String source,String destination,Date depDate);
    
    List<Trip> findTripByRoute(String source,String destination);
}
