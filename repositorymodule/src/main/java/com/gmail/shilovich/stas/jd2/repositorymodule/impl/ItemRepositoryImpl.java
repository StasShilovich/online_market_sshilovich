package com.gmail.shilovich.stas.jd2.repositorymodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.ItemRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

    @Override
    public List<Item> findItemByName(int offset, int limit) {
        String hql = "FROM Item ORDER BY name";
        Query q = entityManager.createQuery(hql)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return q.getResultList();
    }

    @Override
    public List<Item> getItemAPI() {
        String hql = "FROM Item ";
        Query q = entityManager.createQuery(hql);
        return q.getResultList();
    }
}
