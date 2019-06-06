package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Item;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ItemConverterImpl implements ItemConverter {
    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice().toString());
        itemDTO.setUnique(item.getUnique());
        itemDTO.setDescription(item.getDescription());
        return itemDTO;
    }

    @Override
    public Item fromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setPrice(BigDecimal.valueOf(Integer.valueOf(itemDTO.getPrice())));
        item.setUnique(itemDTO.getUnique());
        item.setDescription(itemDTO.getDescription());
        return item;
    }
}
