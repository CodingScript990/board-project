package com.board.dto;

import com.board.domain.UserAccount;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.board.domain.UserAccount} entity
 */
public record UserAccountDto(
				Long id,
				String userId,
				String userPassword,
				String email,
				String nickname,
				String memo,
				LocalDateTime createdAt,
				String createdBy,
				LocalDateTime modifiedAt,
				String modifiedBy
) implements Serializable {
		public static UserAccountDto of(Long id, String userId, String userPassword, String email, String nickname, String memo, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
				return new UserAccountDto(id, userId, userPassword, email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy);
		}

		public static UserAccountDto from(UserAccount entity) {
				return new UserAccountDto(
								entity.getId(),
								entity.getUserId(),
								entity.getUserPassword(),
								entity.getEmail(),
								entity.getNickname(),
								entity.getMemo(),
								entity.getCreatedAt(),
								entity.getCreatedBy(),
								entity.getModifiedAt(),
								entity.getModifiedBy()
				);
		}

		public UserAccount toEntity() {
				return UserAccount.of(
								userId,
								userPassword,
								email,
								nickname,
								memo
				);
		}
}