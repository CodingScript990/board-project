package com.board.controller;

import com.board.config.SecurityConfig;
import com.board.dto.ArticleWithCommentsDto;
import com.board.dto.UserAccountDto;
import com.board.service.ArticleService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// ArticleControllerTest => Article
// Test package 는 무조건 @Autowired 를 명시해줘야함!!
// WebMvcTest => Article Controller Class [include 된 아이들만 실행!]
// SecurityConfig => 인증까지 사용할 수 있게 환경을 갖춤
@DisplayName("View Controller - Post")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {
		// Field add => MVC
		private final MockMvc mvc;

		// ArticleService 를 사용하기 위한 작업
		// Autowired 는 이미 구현된 상태이기에, ArticleService Field 주입 밖에 안되기에 원하는 Autowired 를 사용할 수 없음!
		@MockBean private ArticleService articleService;

		// ArticleControllerTest Constructor add => mvc
		ArticleControllerTest(@Autowired MockMvc mvc) {
				this.mvc = mvc;
		}

		// View Get method => Post List
//		@Disabled("구현 중")
		@DisplayName("View[GET] - Post List (Board) page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
				// Given =>
				given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());
				// When & Then => View 로 이름을 붙여줘서 어떤 의미로 Test 하는 건지 알려줌
				// 여기서 문제는? ContentType method 인데, 이유가 현재 설정한 index.html 에서 charset=utf-8 까지의 가변길이까지 현재 Method 에서는 지원되지 않기 때문에, method 를 ContentTypeCompatibleWith 로 이용하여 호환성에 문제 없도록 변경해줌!(즉, 자기가  컴파일러를 해준다는 소리임)
				// get 요청을 하는데? /articles 로부터!
				mvc.perform(get("/articles"))
								// 요청한 /articles 에서 Ok 싸인을 주고!
								.andExpect(status().isOk())
								// 요청한 /articles 가 Text_Html Type 으로 보여주게 만들고?
								.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
								// 요청한 /articles 에서 해당되는 경로의 파일을 불러오고!(or 있어야하고!)
								.andExpect(view().name("articles/index"))
								// 요청한 /articles 에서 해당되는 articles 의 Data 를 넘겨줌!
								.andExpect(model().attributeExists("articles"))
								// searchType enum 을 List 로 보내주기 위한 작업(어떤 Search Type 인지 Check 하기 위함)
								.andExpect(model().attributeExists("searchTypes"));
				// Then
				then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
		}

		// View Get method => Post Detail
//		@Disabled("구현 중")
		@DisplayName("View[GET] - Post Detail page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
				// Given
				// 가상으로 id 를 생성해줌
				Long articleId = 1L;
				// Comment 를 들고 있는 게시글을 만듬
				given(articleService.getArticle(articleId)).willReturn(createArticleWithCommentsDto());

				// When & Then => view article, articleComment detail
				mvc.perform(get("/articles/" + articleId))
								.andExpect(status().isOk())
								.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
								.andExpect(view().name("articles/detail"))
								.andExpect(model().attributeExists("article"))
								.andExpect(model().attributeExists("articleComments"));
				// Then => 마무리로 확인 작업
				then(articleService).should().getArticle(articleId);
		}

		// View Get method => Post Search
		@Disabled("구현 중")
		@DisplayName("View[GET] - Post Search Only Page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
				// Given

				// When & Then => view post search
				mvc.perform(get("/articles/search"))
								.andExpect(status().isOk())
								.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
								.andExpect(view().name("articles/search"));
		}

		// View Get method => Post Hashtag Search
		@Disabled("구현 중")
		@DisplayName("View[GET] - Post HashTag Search Page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {
				// Given

				// When & Then => View hashtag
				mvc.perform(get("/articles/search-hashtag"))
								.andExpect(status().isOk())
								.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
								.andExpect(view().name("articles/search-hashtag"));
		}

		// Test 를 위한 작업
		// ArticleWithCommentsDto => method createArticleWithCommentsDto
		private ArticleWithCommentsDto createArticleWithCommentsDto() {
				return ArticleWithCommentsDto.of(
								1L,
								createUserAccountDto(),
								Set.of(),
								"title",
								"content",
								"#java",
								LocalDateTime.now(),
								"test",
								LocalDateTime.now(),
								"test"
				);
		}

		// Test Data
		// UserAccountDto => method createUserAccountDto
		private UserAccountDto createUserAccountDto() {
				return UserAccountDto.of(
								1L,
								"test",
								"pw",
								"a41787192@gmail.com",
								"Test",
								"memo",
								LocalDateTime.now(),
								"test",
								LocalDateTime.now(),
								"test"
				);
		}
}