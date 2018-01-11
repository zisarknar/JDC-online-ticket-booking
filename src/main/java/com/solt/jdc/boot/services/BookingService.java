package com.solt.jdc.boot.services;

import com.solt.jdc.boot.domains.Booking;

import java.util.Date;
import java.util.List;

public interface BookingService {

    List<Booking> getAllBooking();

    Booking getBooking(int id);

    void saveBooking(Booking booking);

    void deleteBooking(int id);

    Booking updateBooking(Booking booking);

<<<<<<< HEAD
    Booking findByNameAndPhone(String name,String phone); 
=======
    List<Booking> getBookingReport(Date fromDate, Date toDate);

    long getBookingCount();

>>>>>>> feature/third-week-features
}
