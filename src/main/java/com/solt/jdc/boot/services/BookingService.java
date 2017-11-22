package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> getAllBooking();
    Booking getBooking(int id);
}
