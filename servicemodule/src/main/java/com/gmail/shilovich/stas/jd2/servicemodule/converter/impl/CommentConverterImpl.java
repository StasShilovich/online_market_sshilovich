package com.gmail.shilovich.stas.jd2.servicemodule.converter.impl;

import com.gmail.shilovich.stas.jd2.repositorymodule.model.Comment;
import com.gmail.shilovich.stas.jd2.servicemodule.converter.CommentConverter;
import com.gmail.shilovich.stas.jd2.servicemodule.model.CommentDTO;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class CommentConverterImpl implements CommentConverter {
    @Override
    public CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setDate(comment.getDate().toString());
        commentDTO.setContent(comment.getContent());
        return commentDTO;
    }

    @Override
    public Comment fromDTO(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setDate(Date.valueOf(commentDTO.getDate()));
        comment.setContent(commentDTO.getContent());
        return comment;
    }
}
