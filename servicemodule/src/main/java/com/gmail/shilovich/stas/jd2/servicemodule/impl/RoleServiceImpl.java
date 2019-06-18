package com.gmail.shilovich.stas.jd2.servicemodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.RoleRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.Role;
import com.gmail.shilovich.stas.jd2.servicemodule.RoleService;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.RoleConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    @Override
    @Transactional
    public List<String> getRoleNameList() {
        List<Role> roleList = roleRepository.findAllEntites();
        List<String> roles = roleList.stream()
                .map(roleConverter::toDTO)
                .map(RoleDTO::getName)
                .collect(Collectors.toList());
        return roles;
    }
}
