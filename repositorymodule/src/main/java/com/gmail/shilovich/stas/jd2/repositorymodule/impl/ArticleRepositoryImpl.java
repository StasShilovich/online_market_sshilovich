package com.gmail.shilovich.stas.jd2.repositorymodule.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.ArticleRepository;
import com.gmail.shilovich.stas.jd2.repositorymodule.model.Article;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImpl extends GenericRepositoryImpl<Long, Article> implements ArticleRepository {
}
