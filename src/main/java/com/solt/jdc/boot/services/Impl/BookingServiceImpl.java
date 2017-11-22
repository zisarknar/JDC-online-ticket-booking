package com.solt.jdc.boot.services.Impl;

import com.solt.jdc.boot.domains.Booking;
import com.solt.jdc.boot.repositories.BookingRepository;
import com.solt.jdc.boot.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public void getBookingRepository(BookingRepository bookingRepository) {
       this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBooking(int id) {
        return bookingRepository.getOne(id);
    }
}
