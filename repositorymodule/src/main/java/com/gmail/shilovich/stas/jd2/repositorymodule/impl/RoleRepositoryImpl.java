package com.gmail.shilovich.stas.jd2.repositorymodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.RoleRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class RoleRepositoryImpl extends GenericRepositoryImpl<Long, Role> implements RoleRepository {

    @Override
    public Role findRoleByName(String name) {
        String hql = "FROM Role R WHERE R.name=:name";
        Query query = entityManager.createQuery(hql);
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }
}
