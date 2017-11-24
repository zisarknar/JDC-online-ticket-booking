package com.solt.jdc.boot.repositories;

import com.solt.jdc.boot.domains.RootAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RootAdminRepository extends JpaRepository<RootAdmin, Integer> {
}
