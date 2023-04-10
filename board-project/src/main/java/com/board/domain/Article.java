package com.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
// Article Class => Lombok
// AuditingFields => inherit[Columns = createdAt, createdBy, modifiedAt, modifiedBy]
public class Article extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Filed add => id, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy
    private Long id; // PK

    @Setter @ManyToOne(optional = false) private UserAccount userAccount; // User Info(ID)

    @Setter @Column(nullable = false) private String title; // varchar[제목]
    @Setter @Column(nullable = false, length = 10000) private String content; // varchar[본문]

    @Setter private String hashtag; // varchar[해시태그]

    // JPA 기능 => OneToMany
    // Set<ArticleComment> : 중복말고 한번에 Collection 을 보겠다는 의미
    // @ToString.Exclude : Article 은 단방향! 양방향성은 ArticleComment 만! 그래서
    // Article -> ArticleComment / ArticleComment <-> Article
    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    // 기본 생성자
    protected Article() {}

    // Constructor => userAccount, title, content, hashtag
    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    // Factory Method => new keyword 사용없이 사용하기 위한 작업
    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        // 생성자를 사용하기 위한 작업
        return new Article(userAccount, title, content, hashtag);
    }

    // Equals & HashCode 작업
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        // null 이 아니면 동일성 검사하겠다 && article id 가 동일하면 동일성 검사를 하겠다!
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

