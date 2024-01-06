package com.ll.sbrestapi.domain.member.article.article.dto;

import com.ll.sbrestapi.domain.member.article.article.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleDto {
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Long authorId;
    private String authorName;
    private String title;
    private String body;

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.createDate = article.getCreateDate();
        this.modifyDate = article.getModifyDate();
        this.authorId = article.getAuthor().getId();
        this.authorName = article.getAuthor().getName();
        this.title = article.getTitle();
        this.body = article.getBody();
    }
}
