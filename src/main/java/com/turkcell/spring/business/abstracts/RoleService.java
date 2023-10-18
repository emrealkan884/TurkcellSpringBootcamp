package com.turkcell.spring.business.abstracts;

import com.turkcell.spring.entities.concretes.Role;

public interface RoleService {
    Role getById(int id);
    Role getByRoleName(String roleName);
}
