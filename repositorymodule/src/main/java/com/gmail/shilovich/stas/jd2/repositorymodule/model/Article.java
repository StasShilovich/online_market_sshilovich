package com.gmail.shilovich.stas.jd2.repositorymodule.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "t_article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "F_DATE")
    @OrderBy(value = "date DESC")
    private Date date;
    @Column(name = "F_TITLE")
    private String title;
    @Column(name = "F_CONTENT")
    private String content;
    @Column(name = "F_AUTHOR_INFO")
    private String authorInfo;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "F_ARTICLE_ID")
    private Set<Comment> commentSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(date, article.date) &&
                Objects.equals(title, article.title) &&
                Objects.equals(content, article.content) &&
                Objects.equals(authorInfo, article.authorInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, title, content, authorInfo);
    }
}
