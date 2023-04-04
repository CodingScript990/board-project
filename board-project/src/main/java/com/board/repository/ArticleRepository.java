package com.board.repository;

import com.board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// ArticleRepository Interface => Inherit[JpaRepository]
// @RepositoryRestResource => Spring Data Rest API 를 사용하기 위한 작업
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {
}