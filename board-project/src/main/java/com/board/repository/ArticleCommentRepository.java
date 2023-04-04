package com.board.repository;

import com.board.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// ArticleCommentRepository Interface  => Inherit[JpaRepository]
// @RepositoryRestResource => Spring Data Rest API 를 사용하기 위한 작업
@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
