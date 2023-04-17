package com.board.repository;

import com.board.config.JpaConfig;
import com.board.domain.Article;
import com.board.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA Connection Test")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
		// Test Filed
		private final ArticleRepository articleRepository;
		private final ArticleCommentRepository articleCommentRepository;
		private final UserAccountRepository userAccountRepository;

		public JpaRepositoryTest(
				@Autowired ArticleRepository articleRepository,
				@Autowired ArticleCommentRepository articleCommentRepository,
				@Autowired UserAccountRepository userAccountRepository)
		{
			this.articleRepository = articleRepository;
			this.articleCommentRepository = articleCommentRepository;
			this.userAccountRepository = userAccountRepository;
		}

		// Test => select
		@DisplayName("Select Test")
		@Test
		void givenTestData_whenSelecting_thenWorksFine() {
			// Given

			// When => import.sql data 가 얼마나 있는지 체크
				List<Article> articles = articleRepository.findAll();

			// Then
				assertThat(articles)
								.isNotNull()
								.hasSize(123);
		}

		// Test => insert
		@DisplayName("Insert Test")
		@Test
		void givenTestData_whenInserting_thenWorksFine() {
			// Given
			// previousCount long type 으로 article table data count
				long previousCount = articleRepository.count();
				UserAccount userAccount = userAccountRepository.save(UserAccount.of("newTest", "pw", null, null, null));
				Article article = Article.of(userAccount, "new article", "new content", "#spring");

			// When
			articleRepository.save(article);

			// Then
			assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
		}

		// Test => Update
//		@DisplayName("Update Test")
//		@Test
//		void givenTestData_whenUpdating_thenWorksFine() {
//				// Given
//				// article table data 중 index 1 을 찾아옴
//				Article article = articleRepository.findById(1L).orElseThrow();
//				Hashtag updatedHashtag = Hashtag.of("springboot");
//				article.clearHashtags();
//				article.addHashtags(Set.of(updatedHashtag));
//				// hashtag 를 수정할 것 작업
//				Article savedArticle = articleRepository.saveAndFlush(article);
//
//				// When
//				// article table 에 수정한 것을 db 에 article table 에 저장함
//				Article savedArticle = articleRepository.saveAndFlush(article);
//				// Then
//				// save 된 article table 이 수정잘 되었는지 수정된 hashtag 를 체크
//				assertThat(savedArticle.getHashtags())
//								.hasSize(1)
//								.extracting("hashtagName", String.class)
//								.containsExactly(updatedHashtag.getHashtagName());
//		}

		// Test => Delete
		@DisplayName("Delete Test")
		@Test
		void givenTestData_whenDeleting_thenWorksFine() {
				// Given
				// article table data 중 index 1 을 찾아옴
				Article article = articleRepository.findById(1L).orElseThrow();
				// article table data 수가 몇개인지 check
				long previousArticleCount = articleRepository.count();
				// article comment table data 수가 몇개인지 check
				long previousArticleCommentCount = articleCommentRepository.count();
				// delete 된 article comment size
				int deletedCommentsSize = article.getArticleComments().size();

				// When
				// article table 에 index 1 의 값들을 삭제해줌[해당되는 comment data(article comments) 까지]
				articleRepository.delete(article);
				// Then
				// article table 에서 1개의 index 가 지워진 후 table data 가 -1 이된 table data 값이 맞는지 체크
				assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
				// articleComment table 이 삭제된 값과 delete 된 comments size 값이 동일한지 check
				assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);
		}

}