package com.gmail.shilovich.stas.jd2.servicemodule;

import com.gmail.shilovich.stas.jd2.servicemodule.model.PageDTO;

public interface GenericPageService<T> {
    PageDTO<T> getPage(int page);
}
