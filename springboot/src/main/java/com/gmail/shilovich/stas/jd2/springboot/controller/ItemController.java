package com.gmail.shilovich.stas.jd2.springboot.controller;

import com.gmail.shilovich.stas.jd2.servicemodule.ItemService;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ItemDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.PageDTO;
import com.gmail.shilovich.stas.jd2.springboot.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.gmail.shilovich.stas.jd2.servicemodule.constant.ServiceModuleConstant.OBJECTS_ON_PAGE;

@Controller
@RequestMapping("/private")
public class ItemController {

    private static final Logger logger = LogManager.getLogger(ItemController.class);

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String getFirstItems(
            Model model,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        model.addAttribute("page", page);
        model.addAttribute("scale", OBJECTS_ON_PAGE);
        PageDTO<ItemDTO> pageDTO = itemService.getPage(page);
        model.addAttribute("pageDTO", pageDTO);
        return "/items";
    }
    @GetMapping("/items/{id}")
    public String getItem(@PathVariable(name = "id") Long id, Model model) {
        if (itemService.getItem(id) != null) {
            ItemDTO itemDTO = itemService.getItem(id);
            model.addAttribute("itemDTO", itemDTO);
            return "item";
        } else {
            throw new ControllerException("Item not found with id: " + id);
        }
    }

}
