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
//		@Disabled("구현 중")
		@DisplayName("View[GET] - Post List (Board) page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
				// Given

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
								.andExpect(model().attributeExists("articles"));
		}

		// View Get method => Post Detail
//		@Disabled("구현 중")
		@DisplayName("View[GET] - Post Detail page - Successful Calling")
		@Test
		public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
				// Given

				// When & Then => view article, articleComment detail
				mvc.perform(get("/articles/1"))
								.andExpect(status().isOk())
								.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
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
}