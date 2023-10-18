package com.turkcell.spring.repositories;

import com.turkcell.spring.entities.concretes.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findById(int id);
    Role findByRoleName(String roleName);
}
