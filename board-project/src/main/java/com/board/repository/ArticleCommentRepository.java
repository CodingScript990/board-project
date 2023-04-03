package com.board.repository;

import com.board.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

// ArticleCommentRepository Interface
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}