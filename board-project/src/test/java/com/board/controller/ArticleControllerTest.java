package com.board.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// ArticleControllerTest => Article
// Test package 는 무조건 @Autowired 를 명시해줘야함!!
// WebMvcTest => Article Controller Class [include 된 아이들만 실행!]
@DisplayName("View Controller - Post")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {
		// Field add => MVC
		private final MockMvc mvc;

		// ArticleControllerTest Constructor add => mvc
		ArticleControllerTest(@Autowired MockMvc mvc) {
				this.mvc = mvc;
		}

		// View Get method => Post List
		@Disabled("구현 중")
		@DisplayName("View[GET] - Post List (Board) page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
				// Given

				// When & Then => View 로 이름을 붙여줘서 어떤 의미로 Test 하는 건지 알려줌
				mvc.perform(get("/articles"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.TEXT_HTML))
								.andExpect(view().name("articles/index"))
								.andExpect(model().attributeExists("articles"));
		}

		// View Get method => Post Detail
		@Disabled("구현 중")
		@DisplayName("View[GET] - Post Detail page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
				// Given

				// When & Then => view article, articleComment detail
				mvc.perform(get("/articles/1"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.TEXT_HTML))
								.andExpect(view().name("articles/detail"))
								.andExpect(model().attributeExists("article"))
								.andExpect(model().attributeExists("articleComments"));
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
								.andExpect(content().contentType(MediaType.TEXT_HTML))
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
								.andExpect(content().contentType(MediaType.TEXT_HTML))
								.andExpect(view().name("articles/search-hashtag"));
		}
}