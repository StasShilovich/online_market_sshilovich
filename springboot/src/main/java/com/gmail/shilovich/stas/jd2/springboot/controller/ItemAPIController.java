package com.gmail.shilovich.stas.jd2.springboot.controller;

import com.gmail.shilovich.stas.jd2.servicemodule.ItemService;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/private")
public class ItemAPIController {
    private static final Logger logger = LoggerFactory.getLogger(ItemAPIController.class);

    private final ItemService itemService;

    @Autowired
    public ItemAPIController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<ItemDTO> getItems() {
        List<ItemDTO> items = itemService.getItemAPI();
        return items;
    }

    @GetMapping("/items/{id}")
    public ItemDTO getItem(@PathVariable(name = "id") Long id) {
        ItemDTO itemDTO = itemService.getItem(id);
        return itemDTO;
    }
    @PostMapping("/items")
    public ResponseEntity<String> addItem(@RequestBody ItemDTO itemDTO) {
        itemService.addItem(itemDTO);
        return new ResponseEntity<>(itemDTO.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable(name = "id") Long id) {
        ItemDTO itemDTO = itemService.getItem(id);
            itemService.deleteItem(id);
            return new ResponseEntity<>(itemDTO.toString(), HttpStatus.OK);
    }
}
