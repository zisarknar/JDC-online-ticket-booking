package com.solt.jdc.boot.repositories;
import com.solt.jdc.boot.domains.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



public interface BookingRepository extends JpaRepository<Booking, Integer> {
<<<<<<< HEAD
	
	@Query("select b from book b where b.customer.username=?1 and b.customer.phone=?2")
   Booking findByNameAndPhone(String userName,String phone);
=======

//    @Query("select b from book b where b.bookDate between :fromDate and :toDate")
//    List<Booking> getBookingByDateRange(@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

    @Query(value = "select * from book where book_date between ?1 and ?2", nativeQuery = true)
    List<Booking> getBookingByDateRange(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate);

>>>>>>> feature/third-week-features
}

