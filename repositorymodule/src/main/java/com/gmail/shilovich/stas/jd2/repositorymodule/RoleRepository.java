package com.gmail.shilovich.stas.jd2.repositorymodule;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Role;

public interface RoleRepository extends GenericRepository<Long, Role> {
    Role findRoleByName(String name);
}
