package com.gmail.shilovich.stas.jd2.servicemodule.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

public class ArticleDTO {
    private Long id;
    private String date;
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[A-Za-z0-9]*\\s*")
    private String title;
    @Size(min = 3, max = 1000)
    @Pattern(regexp = "[A-Za-z0-9]*\\s*")
    private String content;
    private String authorInfo;
    private Set<CommentDTO> commentDTOSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    public Set<CommentDTO> getCommentDTOSet() {
        return commentDTOSet;
    }

    public void setCommentDTOSet(Set<CommentDTO> commentDTOSet) {
        this.commentDTOSet = commentDTOSet;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorInfo='" + authorInfo + '\'' +
                ", commentDTOSet=" + commentDTOSet +
                '}';
    }
}
