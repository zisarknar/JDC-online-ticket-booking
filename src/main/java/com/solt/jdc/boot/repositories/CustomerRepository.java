package com.solt.jdc.boot.repositories;

import com.solt.jdc.boot.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	/*@Query("SELECt c FROM customer WHRER c.username=?1")*/
	 @Transactional(readOnly = true)
	   Customer findByusername(/*@Param("username")*/String username);
/*
	 @Query("SELECT t FROM Thing t WHERE t.fooIn = ?1 AND t.bar = ?2")
	    ThingEntity findByFooInAndBar(String fooIn, String bar);
*/	
}
