package com.gmail.shilovich.stas.jd2.servicemodule.converter;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Item;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;

public interface ItemConverter {
    ItemDTO toDTO(Item item);

    Item fromDTO(ItemDTO itemDTO);
}
