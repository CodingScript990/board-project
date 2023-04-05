package com.board.repository;

import com.board.domain.Article;
import com.board.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// ArticleRepository Interface => Inherit[JpaRepository]
// @RepositoryRestResource => Spring Data Rest API 를 사용하기 위한 작업
@RepositoryRestResource
public interface ArticleRepository extends
				// JPA
				JpaRepository<Article, Long>,
				// Article
				QuerydslPredicateExecutor<Article>,
				// QClass => <T> 는 Entity type 이 들어간다는 의미!
				QuerydslBinderCustomizer<QArticle> {
		// Search 할때 Custom 한대로 동작하도록 함
		// customize method add
		@Override
		default void customize(QuerydslBindings bindings, QArticle root) {
			// Article 에 있는 모든 Field 들을 읽어오는 작업 => 부분 검색을 위한 작업
			bindings.excludeUnlistedProperties(true);
			// 원하는 custom searching 하기 위해 작업
			bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
			// title 을 바인딩 하는 작업 => 대소문자 구분없이 검색되도록 함
			bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // 생성자(like) => '%${v}%'
			// 수동으로 작업할때
			//bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // 생성자(like) => '${v}'
			bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
			bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
			bindings.bind(root.createdAt).first(DateTimeExpression::eq);
			bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
		}
}