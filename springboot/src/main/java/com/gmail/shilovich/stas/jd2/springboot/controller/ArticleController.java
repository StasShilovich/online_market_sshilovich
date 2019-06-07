package com.gmail.shilovich.stas.jd2.springboot.controller;

import com.gmail.shilovich.stas.jd2.servicemodule.ArticleService;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ArticleDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.gmail.shilovich.stas.jd2.servicemodule.constant.ServiceModuleConstant.OBJECTS_ON_PAGE;

@Controller
@RequestMapping("/private")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public String getFirstItems(
            Model model,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        model.addAttribute("page", page);
        model.addAttribute("scale", OBJECTS_ON_PAGE);
        PageDTO<ArticleDTO> pageDTO = articleService.getPage(page);
        model.addAttribute("pageDTO", pageDTO);
        return "/articles";
    }

}
