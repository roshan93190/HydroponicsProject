package com.HydroPonics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HydroPonics.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer> {

}
