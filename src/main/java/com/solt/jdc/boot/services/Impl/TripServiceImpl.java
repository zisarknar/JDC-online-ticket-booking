package com.solt.jdc.boot.services.Impl;

import com.solt.jdc.boot.domains.Trip;
import com.solt.jdc.boot.repositories.TripRepository;
import com.solt.jdc.boot.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TripServiceImpl implements TripService {


    private TripRepository tripRepository;

    @Autowired
    public void getTripRepsitory(TripRepository tripRepository){
        this.tripRepository = tripRepository;
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Override
    public Trip getTrip(int id) {
        return tripRepository.getOne(id);
    }

    @Override
    public void saveTrip(Trip trip) {
        tripRepository.saveAndFlush(trip);
    }

    @Override
    public void deleteTrip(int id) {
        tripRepository.delete(id);
    }

    @Override
    public Trip updateTrip(Trip trip) {
        return tripRepository.saveAndFlush(trip);
    }

	@Override
	public List<Trip> findTripByFilter(String source, String destination, Date depDate) {
		
		return tripRepository.findTripByFilter(source, destination, depDate);
	}
}
