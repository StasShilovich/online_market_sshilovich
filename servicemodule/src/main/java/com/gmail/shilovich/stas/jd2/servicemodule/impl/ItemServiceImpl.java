package com.gmail.shilovich.stas.jd2.servicemodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.ItemRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.Item;
import com.gmail.shilovich.stas.jd2.servicemodule.GenericPageService;
import com.gmail.shilovich.stas.jd2.servicemodule.ItemService;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ItemConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.PageDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.shilovich.stas.jd2.servicemodule.constant.ServiceModuleConstant.OBJECTS_ON_PAGE;

@Service
public class ItemServiceImpl implements ItemService, GenericPageService<ItemDTO> {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private final ItemRepository itemRepository;
    private final ItemConverter itemConverter;

    public ItemServiceImpl(ItemRepository itemRepository, ItemConverter itemConverter) {
        this.itemRepository = itemRepository;
        this.itemConverter = itemConverter;
    }

    @Override
    @Transactional
    public PageDTO<ItemDTO> getPage(int page) {
        PageDTO<ItemDTO> pageDTO = new PageDTO<>();
        int countOfItems = getCountOfItems();
        int offset = (page - 1) * OBJECTS_ON_PAGE;
        int limit = OBJECTS_ON_PAGE;
        logger.info("offset=" + offset + "  limit=" + limit);
        pageDTO.setList(getItems(offset, limit));
        logger.info(pageDTO.getList().size() + " -size");
        int countOfPages = new BigDecimal((double) countOfItems / OBJECTS_ON_PAGE).setScale(0, RoundingMode.UP).intValue();
        pageDTO.setCountOfPages(countOfPages);
        return pageDTO;
    }

    @Override
    @Transactional
    public ItemDTO getItem(Long id) {
        Item item = itemRepository.findById(id);
        return itemConverter.toDTO(item);
    }

    @Override
    @Transactional
    public List<ItemDTO> getItemAPI() {
        List<Item> itemAPI = itemRepository.getItemAPI();
        List<ItemDTO> itemDTOS = itemAPI.stream()
                .map(itemConverter::toDTO)
                .collect(Collectors.toList());
        return itemDTOS;
    }

    @Override
    @Transactional
    public void addItem(ItemDTO itemDTO) {
        Item item = itemConverter.fromDTO(itemDTO);
        itemRepository.persist(item);
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id);
        itemRepository.remove(item);
    }

    private int getCountOfItems() {
        return itemRepository.getCountOfEntities();
    }

    private List<ItemDTO> getItems(int offset, int limit) {
        List<Item> items = itemRepository.findItemByName(offset, limit);
        List<ItemDTO> itemDTOS = items.stream()
                .map(itemConverter::toDTO)
                .collect(Collectors.toList());
        return itemDTOS;
    }
}
