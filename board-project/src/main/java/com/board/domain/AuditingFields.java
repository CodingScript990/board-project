package com.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingFields {
		// updatable = false => 최초 1회만 하고 그 이상은 update 되지 않게!
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		@CreatedDate
		@Column(nullable = false, updatable = false)
		private LocalDateTime createdAt; // datetime[생성일시]

		@CreatedBy
		@Column(nullable = false, updatable = false, length = 100)
		private String createdBy; // varchar[생성자]

		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		@LastModifiedDate
		@Column(nullable = false)
		private LocalDateTime modifiedAt; // datetime[수정일시]

		@LastModifiedBy
		@Column(nullable = false, length = 100)
		private String modifiedBy; // varchar[수정자]
}
