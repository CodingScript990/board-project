package com.board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

// PaginationServiceTest => TDD[Pagination]
// webEnvironment : Web Test 환경 구성이 가능함을 말함, Parameter 를 이용하여 손쉽게 Web Test 환경을 선택할 수 있음
// NONE : SpringApplication 로 Application 컨텍스트를 생성함, mock 이나 다른 것들을 포함해 웹 환경도 제공하지 않음을 말함
@DisplayName("Business Logic - Pagination")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class)
class PaginationServiceTest {
		// Pagination Service call
		private final PaginationService paginationService;

		// PaginationService Constructor add => PaginationService 를 받아와 주는 작업
		public PaginationServiceTest(@Autowired PaginationService paginationService) {
				this.paginationService = paginationService;
		}

		// Test =>
		// ParameterizedTest => 하나의 테스트 Method 로 여러 개의 Parameter 에 대해서 (Test) [currentPageNumber, totalPages]
		// expected : 검증하고자 하는 값을 따로 설정함
		@DisplayName("Now Page Number와 Total Page Number를 주면, Paging Bar List를 만들어 줌")
		@MethodSource
		@ParameterizedTest(name = "[{index}] {0}, {1} => {2}") // test number 가 현재 입력값을 출력하고, 마지막 값을 표현하고 나머지 부분은 List 상태로 표현이 됨을 알 수 있음
		void givenCurrentPageNumberAndTotalPages_whenCalculating_thenReturnsPaginationBarNumbers(int currentPageNumber, int totalPages, List<Integer> expected) {
				// Given

				// When => actual[실제값](현재 페이지 수, 총 페이지 수)
				List<Integer> actual = paginationService.getPaginationBarNumbers(currentPageNumber, totalPages);

				// Then => 실제 값이 검증하고자하는 값과 동일한가?
				assertThat(actual).isEqualTo(expected);

		}

		// 입력값을 넣어주는 Method 를 만들어줌
		// Stream 병렬 스트림을 생성하는데 argument 전달 or 입력되는 실제 값을 받아오는 작업을 말함
		static Stream<Arguments> givenCurrentPageNumberAndTotalPages_whenCalculating_thenReturnsPaginationBarNumbers () {
				// 검증하고자 하는 값들을 넣어주는 작업
				return Stream.of(
								arguments(0, 13, List.of(0,1,2,3,4)),
								arguments(1, 13, List.of(0,1,2,3,4)),
								arguments(2, 13, List.of(0,1,2,3,4)),
								arguments(3, 13, List.of(1,2,3,4,5)),
								arguments(4, 13, List.of(2,3,4,5,6)),
								arguments(5, 13, List.of(3,4,5,6,7)),
								arguments(6, 13, List.of(4,5,6,7,8)),
								arguments(10, 13, List.of(8,9,10,11,12)),
								arguments(11, 13, List.of(9,10,11,12)),
								arguments(12, 13, List.of(10,11,12))
						arguments(0, 13, List.of(0,1,2,3,4)),
						arguments(1, 13, List.of(0,1,2,3,4)),
						arguments(2, 13, List.of(0,1,2,3,4)),
						arguments(3, 13, List.of(1,2,3,4,5)),
						arguments(4, 13, List.of(2,3,4,5,6)),
						arguments(5, 13, List.of(3,4,5,6,7)),
						arguments(6, 13, List.of(4,5,6,7,8)),
						arguments(10, 13, List.of(8,9,10,11,12)),
						arguments(11, 13, List.of(9,10,11,12)),
						arguments(12, 13, List.of(10,11,12))
				);
		}

		// Test => pagination length
		@DisplayName("현재 설정되어 있는 Pagination Bar 의 length 를 알려줌")
		@Test
		void givenNothing_whenCalling_thenReturnsCurrentBarLength() {
				// Given

				// When => 현재 설정된 길이를 받아와 주는 작업
				int barLength = paginationService.currentBarLength();

				// Then => 스팩의 명세를 하기 위해서 Code 에 표현하는 것임, Test Code 에서 default 값을 5로 설정을 하고 Test 하겠다는 의미(Pagination)
				assertThat(barLength).isEqualTo(5);
		}
}