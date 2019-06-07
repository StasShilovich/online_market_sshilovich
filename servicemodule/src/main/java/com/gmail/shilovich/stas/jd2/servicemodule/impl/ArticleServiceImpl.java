package com.gmail.shilovich.stas.jd2.servicemodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.ArticleRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.Article;
import com.gmail.shilovich.stas.jd2.servicemodule.ArticleService;
import com.gmail.shilovich.stas.jd2.servicemodule.GenericPageService;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ArticleConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ArticleDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.shilovich.stas.jd2.servicemodule.constant.ServiceModuleConstant.OBJECTS_ON_PAGE;

@Service
public class ArticleServiceImpl implements ArticleService, GenericPageService<ArticleDTO> {

    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleConverter articleConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
    }

    @SuppressWarnings("Duplicates")
    @Override
    @Transactional
    public PageDTO<ArticleDTO> getPage(int page) {
        PageDTO<ArticleDTO> pageDTO = new PageDTO<>();
        int countOfArticles = getCountOfArticles();
        int offset = (page - 1) * OBJECTS_ON_PAGE;
        int limit = OBJECTS_ON_PAGE;
        pageDTO.setList(getArticles(offset, limit));
        int countOfPages = new BigDecimal((double) countOfArticles / OBJECTS_ON_PAGE).setScale(0, RoundingMode.UP).intValue();
        pageDTO.setCountOfPages(countOfPages);
        return pageDTO;
    }

    private List<ArticleDTO> getArticles(int offset, int limit) {
        List<Article> articles = articleRepository.findAll(offset, limit);
        List<ArticleDTO> articleDTOS = articles.stream()
                .map(articleConverter::toDTO)
                .collect(Collectors.toList());
        return articleDTOS;
    }

    private int getCountOfArticles() {
        return articleRepository.getCountOfEntities();
    }
}
