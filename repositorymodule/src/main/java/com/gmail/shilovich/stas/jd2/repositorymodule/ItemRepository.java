package com.gmail.shilovich.stas.jd2.repositorymodule;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Item;

import java.util.List;

public interface ItemRepository extends GenericRepository<Long, Item> {
    List<Item> findItemByName(int offset, int limit);

    List<Item> getItemAPI();
}
