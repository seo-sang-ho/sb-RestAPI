package com.ll.sbrestapi.domain.member.article.article.repository;

import com.ll.sbrestapi.domain.member.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByOrderByIdDesc();
}
