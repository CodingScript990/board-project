package com.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

// @Setter : Class 전체적으로 걸면 안됨! (id) 등을 setter로 변경해버릴 수 있기 때문임
// case by case로 전체적으로 거는 경우도 있긴함
@Getter
// ToString.Exclude 를 활성화 해주겠다는 의미
@ToString(callSuper = true)
// @Table 역할 : DB Schema 에서 필요로 한 것을 Mapping 해주는 의미
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
// @Entity 역할 : DB Table 과 Class(VO,DTO)와 Mapping 하여 Field(Table Schema) 들을 사용하겠다는 의미
@Entity
// Article Class => Lombok
// AuditingFields => inherit[Columns = createdAt, createdBy, modifiedAt, modifiedBy]
public class Article extends AuditingFields {
    // @Id : Object 의 Primary Key 를 의미함, JPA 는 id 를 통해 Object 를 구분함
    @Id
    // @GeneratedValue : 주키의 값을 위한 Auto 생성 전략을 명시하는데 사용됨
    /**
     * GeneratedValue 명시(4가지)
     * 1. AUTO - (Persistence provider) 특정 DB에 맞게 자동 선택을 말함
     * 2. IDENTITY - DB 의 Identity column을 이용함
     * 3. SEQUENCE - DB의 SEQUENCE column을 이용함
     * 4. TABLE - 유일성이 보장된 DB Table을 이용함
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Filed add => id, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy
    private Long id; // PK

    // N:1 관계로 UserAccount type userAccount 로 값을 받을 받을 거임
    @Setter @ManyToOne(optional = false) private UserAccount userAccount; // 유저 정보 (ID)

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

