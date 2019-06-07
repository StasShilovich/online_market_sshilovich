package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Article;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.ArticleConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ArticleDTO;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class ArticleConverterImpl implements ArticleConverter {
    @Override
    public ArticleDTO toDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setDate(article.getDate().toString());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setAuthorInfo(article.getAuthorInfo());
        return articleDTO;
    }

    @Override
    public Article fromDTO(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setDate(Date.valueOf(articleDTO.getDate()));
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setAuthorInfo(articleDTO.getAuthorInfo());
        return article;
    }
}
