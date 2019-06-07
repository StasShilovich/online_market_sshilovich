package com.gmail.shilovich.stas.jd2.servicemodule.converter;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Comment;
import com.gmail.shilovich.stas.jd2.servicemodule.model.CommentDTO;

public interface CommentConverter {
    CommentDTO toDTO(Comment comment);

    Comment fromDTO(CommentDTO commentDTO);
}
