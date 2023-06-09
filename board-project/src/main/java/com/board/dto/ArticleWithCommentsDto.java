package com.board.dto;

import com.board.domain.Article;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link com.board.domain.ArticleComment} entity
 */
public record ArticleWithCommentsDto(
				Long id,
				UserAccountDto userAccountDto,
				Set<ArticleCommentDto> articleCommentDtos,
				String title,
				String content,
				String hashtag,
				LocalDateTime createdAt,
				String createdBy,
				LocalDateTime modifiedAt,
				String modifiedBy
) implements Serializable {
		public static ArticleWithCommentsDto of(Long id, UserAccountDto userAccountDto, Set<ArticleCommentDto> articleCommentDtos, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
				return new ArticleWithCommentsDto(id, userAccountDto, articleCommentDtos, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
		}

		public static ArticleWithCommentsDto from(Article entity) {
				return new ArticleWithCommentsDto(
								entity.getId(),
								UserAccountDto.from(entity.getUserAccount()),
								entity.getArticleComments().stream()
												.map(ArticleCommentDto::from)
												.collect(Collectors.toCollection(LinkedHashSet::new)),
								entity.getTitle(),
								entity.getContent(),
								entity.getHashtag(),
								entity.getCreatedAt(),
								entity.getCreatedBy(),
								entity.getModifiedAt(),
								entity.getModifiedBy()
				);
		}
}