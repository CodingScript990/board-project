package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// ArticleController Class => Controller, RequestMapping[/articles => path]
@RequestMapping("/articles")
@Controller
public class ArticleController {
		// Handler Method part
		// ArticleControllerTest => index 부분 Error 요인은 Controller 에서 작업이 없었기에 404 가 뜸!
		// `@GetMapping` 을 통해서 index 를 받아주는 Constructor add
		// Post List
		@GetMapping
		public String articles(ModelMap map) {
				// ModelMap 을 활용하여 attribute 에서 필요로 하는 articles 를 넣고 List Type 으로 Data 를 불러오는 작업을 해줌! 그러면 에러가 뜨지 않음(잘 불러온다는 의미)
				map.addAttribute("articles", List.of());
				return "articles/index";
		}

		// Post Detail
		@GetMapping("/{articleId}")
		public String article(@PathVariable Long articleId, ModelMap map) {
				// article 에서 id 값을 들고 오고, Comments 들도 article id 에 맞는 comments 의 Data 를 들고옴
				map.addAttribute("article", "article"); // TODO: 구현할때 실제 Data를 넣어줄거임!
				map.addAttribute("articleComments", List.of());
				return "articles/detail";
		}
}
