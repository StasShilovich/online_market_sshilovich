package com.gmail.shilovich.stas.jd2.servicemodule;

import com.gmail.shilovich.stas.jd2.servicemodule.model.ArticleDTO;
import com.gmail.shilovich.stas.jd2.servicemodule.model.PageDTO;

public interface ArticleService {
    PageDTO<ArticleDTO> getPage(int page);
}
