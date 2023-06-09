package com.board.repository;

import com.board.domain.ArticleComment;
import com.board.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// ArticleCommentRepository Interface  => Inherit[JpaRepository]
// @RepositoryRestResource => Spring Data Rest API 를 사용하기 위한 작업
@RepositoryRestResource
public interface ArticleCommentRepository extends
				// JPA
				JpaRepository<ArticleComment, Long>,
				// ArticleComment
				QuerydslPredicateExecutor<ArticleComment>,
				// QClass => <T> 는 Entity type 이 들어간다는 의미!
				QuerydslBinderCustomizer<QArticleComment> {
		List<ArticleComment> findByArticle_Id(Long articleId);
		// customize method add
		@Override
		default void customize(QuerydslBindings bindings, QArticleComment root) {
				// Article 에 있는 모든 Field 들을 읽어오는 작업 => 부분 검색을 위한 작업
				bindings.excludeUnlistedProperties(true);
				// 원하는 custom searching 하기 위해 작업
				bindings.including(root.content, root.createdAt, root.createdBy);
				// title 을 바인딩 하는 작업 => 대소문자 구분없이 검색되도록 함
				bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // 생성자(like) => '%${v}%'
				// 수동으로 작업할때
				//bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // 생성자(like) => '${v}'
				bindings.bind(root.createdAt).first(DateTimeExpression::eq);
				bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
		}
}
