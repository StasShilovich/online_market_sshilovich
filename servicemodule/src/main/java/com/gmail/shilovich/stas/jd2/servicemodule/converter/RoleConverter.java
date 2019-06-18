package com.gmail.shilovich.stas.jd2.servicemodule.converter;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Role;
import com.gmail.shilovich.stas.jd2.servicemodule.model.RoleDTO;

public interface RoleConverter {
    RoleDTO toDTO(Role role);
}
