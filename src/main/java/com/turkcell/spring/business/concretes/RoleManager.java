package com.turkcell.spring.business.concretes;

import com.turkcell.spring.business.abstracts.RoleService;
import com.turkcell.spring.entities.concretes.Role;
import com.turkcell.spring.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleManager implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
