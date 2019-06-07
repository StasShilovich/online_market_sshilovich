package com.gmail.shilovich.stas.jd2.servicemodule.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CommentDTO {
    private Long id;
    private String date;
    @Size(max = 200)
    @Pattern(regexp = "[A-Za-z0-9]*\\s*")
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
