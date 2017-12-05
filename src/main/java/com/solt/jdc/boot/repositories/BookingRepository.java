package com.solt.jdc.boot.repositories;

import com.solt.jdc.boot.domains.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
