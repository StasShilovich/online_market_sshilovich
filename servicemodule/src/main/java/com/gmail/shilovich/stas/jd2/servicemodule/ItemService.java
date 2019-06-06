package com.gmail.shilovich.stas.jd2.servicemodule;

import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.PageDTO;

import java.util.List;


public interface ItemService {
    PageDTO<ItemDTO> getPage(int countOfPAge);

    ItemDTO getItem(Long id);

    List<ItemDTO> getItemAPI();

    void addItem(ItemDTO itemDTO);

    void deleteItem(Long id);
}
