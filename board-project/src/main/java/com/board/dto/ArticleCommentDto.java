package com.board.dto;

import com.board.domain.Article;
import com.board.domain.ArticleComment;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.board.domain.ArticleComment} entity
 */
public record ArticleCommentDto(
				Long id,
				Long articleId,
				UserAccountDto userAccountDto,
				String content,
				LocalDateTime createdAt,
				String createdBy,
				LocalDateTime modifiedAt,
				String modifiedBy
) implements Serializable {
		public static ArticleCommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
				return new ArticleCommentDto(id, articleId, userAccountDto, content, createdAt, createdBy, modifiedAt, modifiedBy);
		}

		public static ArticleCommentDto from(ArticleComment entity) {
				return new ArticleCommentDto(
								entity.getId(),
								entity.getArticle().getId(),
								UserAccountDto.from(entity.getUserAccount()),
								entity.getContent(),
								entity.getCreatedAt(),
								entity.getCreatedBy(),
								entity.getModifiedAt(),
								entity.getModifiedBy()
				);
		}

		public ArticleComment toEntity(Article entity) {
				return ArticleComment.of(
								entity,
								userAccountDto.toEntity(),
								content
				);
		}
}