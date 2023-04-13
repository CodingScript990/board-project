package com.board.dto;

import com.board.domain.Article;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.board.domain.Article} entity
 */
// 회원 정보만 가지고 있음!
public record ArticleDto(
				Long id,
				UserAccountDto userAccountDto,
				String title,
				String content,
				String hashtag,
				LocalDateTime createdAt,
				String createdBy,
				LocalDateTime modifiedAt,
				String modifiedBy
) implements Serializable {
		public static ArticleDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
				return new ArticleDto(id, userAccountDto, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
		}

		public static ArticleDto from(Article entity) {
				return new ArticleDto(
								entity.getId(),
								UserAccountDto.from(entity.getUserAccount()),
								entity.getTitle(),
								entity.getContent(),
								entity.getHashtag(),
								entity.getCreatedAt(),
								entity.getCreatedBy(),
								entity.getModifiedAt(),
								entity.getModifiedBy()
				);
		}

		// Entity 를 사용하기 위한 것
		public Article toEntity() {
				return Article.of(
								userAccountDto.toEntity(),
								title,
								content,
								hashtag
				);
		}
}