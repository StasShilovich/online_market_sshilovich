package com.gmail.shilovich.stas.jd2.repositorymodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.ItemRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepository {

    private static final Logger logger = LogManager.getLogger(ItemRepositoryImpl.class);

    @Override
    public int getCountOfEntities() {
        int countOfEntities = super.getCountOfEntities();
        logger.info("Cout of entities= " + countOfEntities);
        return countOfEntities;
    }
}
