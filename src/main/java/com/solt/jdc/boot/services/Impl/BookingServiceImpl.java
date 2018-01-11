package com.solt.jdc.boot.services.Impl;

import com.solt.jdc.boot.domains.Booking;
import com.solt.jdc.boot.repositories.BookingRepository;
import com.solt.jdc.boot.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public void saveBooking(Booking booking) {
        bookingRepository.saveAndFlush(booking);
    }

    @Override
    public void deleteBooking(int id) {
        bookingRepository.delete(id);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return bookingRepository.saveAndFlush(booking);
    }

<<<<<<< HEAD
	@Override
	public Booking findByNameAndPhone(String userName,String phone) {
		
		
		return bookingRepository.findByNameAndPhone(userName,phone);
		
	}
=======
    @Override
    public List<Booking> getBookingReport(Date fromDate, Date toDate) {
        return bookingRepository.getBookingByDateRange(fromDate, toDate);
    }

    @Override
    public long getBookingCount() {
        return bookingRepository.count();
    }
>>>>>>> feature/third-week-features


}
