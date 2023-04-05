package com.board.controller;

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
		@DisplayName("View[GET] - Post List (Board) page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
				// Given

				// When & Then
				mvc.perform(get("/articles"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.TEXT_HTML))
								.andExpect(model().attributeExists("articles"));
		}

		// View Get method => Post Detail
		@DisplayName("View[GET] - Post Detail page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
				// Given

				// When & Then
				mvc.perform(get("/articles/1"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.TEXT_HTML))
								.andExpect(model().attributeExists("article"));
		}

		// View Get method => Post Search
		@DisplayName("View[GET] - Post Search Only Page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
				// Given

				// When & Then
				mvc.perform(get("/articles/search"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.TEXT_HTML));
		}

		// View Get method => Post Hashtag Search
		@DisplayName("View[GET] - Post HashTag Search Page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {
				// Given

				// When & Then
				mvc.perform(get("/articles/search-hashtag"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.TEXT_HTML));
		}
}