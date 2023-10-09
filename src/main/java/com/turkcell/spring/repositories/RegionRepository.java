package com.turkcell.spring.repositories;

import com.turkcell.spring.entities.concretes.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Short> {
}
