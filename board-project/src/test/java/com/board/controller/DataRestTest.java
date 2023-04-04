package com.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// DataRestTest => TDD[SpringBootTest 로 MockMvc 가 잘 실행되도록 작업]
// Data REST Test
// Transactional => 롤백기능 state 로 Test
@DisplayName("Data REST - API Test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {
		// Filed add => mockMvc
		/** MockMvc
		 * 1. Web Application 을 Application Server 에 배포하지 않고 Test 용 MVC 환경을 만들어 요청 및 전송, 응답기능을 제공해주는 유틸리티 Class 임
		 * 2. Controller Test 하고 싶을때 실제 Server 에 구현한 Application 에 올리지 않고, Test 용으로 시뮬레이션하여 MVC 가 되도록 도와주는 Class 를 말함
		 */
		private final MockMvc mvc;

		// MockMvc Constructor add
		public DataRestTest(@Autowired MockMvc mvc) {
				this.mvc = mvc;
		}

		// Test => MockMvc [API -> Board List]
		@DisplayName("[API] Board List Select")
		@Test
		void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
				// Given

				// When & Then => application/hal+json 통신에 문제가 없는지 체크
				mvc.perform(get("/api/articles"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
		}

		// Test => MockMvc [API -> Article]
		@DisplayName("[API] Board Select")
		@Test
		void givenNothing_whenRequestingArticle_thenReturnsArticleJsonResponse() throws Exception {
				// Given

				// When & Then => application/hal+json 통신에 문제가 없는지 체크
				mvc.perform(get("/api/articles/1"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
		}

		// Test => MockMvc [API -> (Board) Comment List]
		@DisplayName("[API] Board -> Comment List Select")
		@Test
		void givenNothing_whenRequestingArticleCommentsFormArticle_thenReturnsArticleCommentsJsonResponse() throws Exception {
				// Given

				// When & Then => application/hal+json 통신에 문제가 없는지 체크
				mvc.perform(get("/api/articles/1/articleComments"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
		}

		// Test => MockMvc [API -> Comment List]
		@DisplayName("[API] Comment List Select")
		@Test
		void givenNothing_whenRequestingArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
				// Given

				// When & Then => application/hal+json 통신에 문제가 없는지 체크
				mvc.perform(get("/api/articleComments"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
		}

		// Test => MockMvc [API -> Comment]
		@DisplayName("[API] Comment Select")
		@Test
		void givenNothing_whenRequestingArticleComment_thenReturnsArticleCommentJsonResponse() throws Exception {
				// Given

				// When & Then => application/hal+json 통신에 문제가 없는지 체크
				mvc.perform(get("/api/articleComments/1"))
								.andExpect(status().isOk())
								.andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
		}
}
