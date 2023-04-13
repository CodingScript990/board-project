package com.board.service;

import com.board.domain.Article;
import com.board.domain.type.SearchType;
import com.board.dto.ArticleDto;
import com.board.dto.ArticleWithCommentsDto;
import com.board.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Slf4j => lombok 기능
@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
		private final ArticleRepository articleRepository;

		@Transactional(readOnly = true)
		public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
				// searchKeyword 가 null, blank 이면?
				if (searchKeyword == null || searchKeyword.isBlank()) {
						// 이미 만들어져 있는 Page 기능을 활용하여 from[ArticleDTO] method 를 이용해서 Page 를 뛰어줌
						return articleRepository.findAll(pageable).map(ArticleDto::from);
				}

				// Switch 문법(SearchType => keyword)
				return switch (searchType) {
						case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
						case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
						case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
						case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
						case HASHTAG -> articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
				};
		}

		// 단건 조회 => articleId
		@Transactional(readOnly = true)
		public ArticleWithCommentsDto getArticle(Long articleId) {
				// 조회 할 수 있도록 Article ID 를 불러옴
				return articleRepository.findById(articleId)
								// ArticleWithCommentsDto 를 들고옴(Entity)
								.map(ArticleWithCommentsDto::from)
								// 문제 발생시 예외처리 => Log 찍어서 문제를 확인하는 방법(보안상 규정이 있을시, Log 를 생략함)
								.orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: " + articleId));
		}

		// 게시글을 생성해주는 작업 => ArticleDto(Entity)
		public void saveArticle(ArticleDto dto) {
				articleRepository.save(dto.toEntity());
		}

		// 게시글을 수정해주는 작업 => ArticleDto(Entity)
		public void updateArticle(ArticleDto dto) {
				// Try ~ catch 문으로 예외처리 기능까지 할 수 있도록 작업함
				try {
						// 영속성을 위한 작업! => articleRepository[DTO.ID]
						Article article = articleRepository.getReferenceById(dto.id());
						// dto 에서 entity title 값이 null 아닐때만 실행하라
						if (dto.title() != null) { article.setTitle(dto.title()); }
						// dto 에서 entity content 값이 null 아닐때만 실행하라
						if (dto.content() != null) { article.setContent(dto.content()); }
						article.setHashtag(dto.hashtag());
						// Update fail 시 예외처리 => Log 를 통해서 dto 상태를 check
				} catch (EntityNotFoundException e) {
						log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없습니다 - dto: {}", dto);
				}
		}

		// 게시글을 삭제해주는 작업 => ArticleDto(DTO.ID)
		public void deleteArticle(long articleId) {
				// 삭제를 해줄 ArticleID 를 들고와 삭제를 해줌
				articleRepository.deleteById(articleId);
		}
}
