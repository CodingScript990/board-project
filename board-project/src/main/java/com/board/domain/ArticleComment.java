package com.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

// ArticleComment Class
@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
// ArticleComment Class => Lombok
// AuditingFields => inherit[Columns = createdAt, createdBy, modifiedAt, modifiedBy]
@Entity
public class ArticleComment extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Field add => id, article_id, content, createdAt, createdBy, modifiedAt, modifiedBy
    private Long id; // PK
    @Setter @ManyToOne(optional = false) private Article article; // FK[게시글 ID]
    @Setter @Column(nullable = false, length = 500) private String content; // varchar[본문]

    // 기본 생성자
    protected ArticleComment() {}

    // Constructor => article, content
    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    // Factory method
    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article, content);
    }

    // Equals => id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && id.equals(that.id);
    }

    // hashCode => id
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
