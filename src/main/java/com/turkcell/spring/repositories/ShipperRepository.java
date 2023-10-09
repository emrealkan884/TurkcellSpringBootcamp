package com.turkcell.spring.repositories;

import com.turkcell.spring.entities.concretes.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipperRepository extends JpaRepository<Shipper,Short> {
}
