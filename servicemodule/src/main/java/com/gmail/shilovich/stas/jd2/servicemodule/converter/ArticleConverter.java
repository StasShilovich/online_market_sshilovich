package com.gmail.shilovich.stas.jd2.servicemodule.converter;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Article;
import com.gmail.shilovich.stas.jd2.servicemodule.model.ArticleDTO;

public interface ArticleConverter {
    ArticleDTO toDTO(Article article);

    Article fromDTO(ArticleDTO articleDTO);
}
