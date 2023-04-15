package com.board.controller;

import com.board.domain.type.SearchType;
import com.board.dto.response.ArticleResponse;
import com.board.dto.response.ArticleWithCommentResponse;
import com.board.service.ArticleService;
import com.board.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// ArticleController Class => Controller, RequestMapping[/articles => path]
@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {
		// ArticleService 를 사용하기 위한 작업(Dependency)
		private final ArticleService articleService;
		// PaginationService 를 사용하기 위한 작업(Dependency)
		private final PaginationService paginationService;
		// Handler Method part
		// ArticleControllerTest => index 부분 Error 요인은 Controller 에서 작업이 없었기에 404 가 뜸!
		// `@GetMapping` 을 통해서 index 를 받아주는 Constructor add
		// Post List
		@GetMapping
		public String articles(
						@RequestParam(required = false) SearchType searchType,
						@RequestParam(required = false) String searchValue,
						@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
						ModelMap map
		) {
				Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);

				// List type (Data type Integer) paginationService 를
				List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());

				map.addAttribute("articles", articles);
				map.addAttribute("paginationBarNumbers", barNumbers);

				return "articles/index";
		}

		// Post Detail
		@GetMapping("/{articleId}")
		public String article(@PathVariable Long articleId, ModelMap map) {
				// article 에서 id 값을 들고 오고, Comments 들도 article id 에 맞는 comments 의 Data 를 들고옴
				ArticleWithCommentResponse article = ArticleWithCommentResponse.from(articleService.getArticle(articleId));
				map.addAttribute("article", article);
				map.addAttribute("articleComments", article.articleCommentsResponse());
				return "articles/detail";
		}
}
