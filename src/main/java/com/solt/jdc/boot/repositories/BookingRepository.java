package com.solt.jdc.boot.repositories;
import com.solt.jdc.boot.domains.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	@Query("select b from book b where b.customer.username=?1 and b.customer.phone=?2")
   Booking findByNameAndPhone(String userName,String phone);
}

