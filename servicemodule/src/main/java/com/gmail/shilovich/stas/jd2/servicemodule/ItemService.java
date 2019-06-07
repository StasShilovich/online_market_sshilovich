package com.gmail.shilovich.stas.jd2.servicemodule;

import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.PageDTO;

import java.util.List;


public interface ItemService {

    ItemDTO getItem(Long id);

    List<ItemDTO> getItemAPI();

    void addItem(ItemDTO itemDTO);

    void deleteItem(Long id);

    PageDTO<ItemDTO> getPage(int page);
}
