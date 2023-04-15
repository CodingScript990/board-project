package com.board.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

// PaginationService => Service[Pagination]
@Service
public class PaginationService {
		// 상태값 설정! => 5개씩!
		private static final int BAR_LENGTH = 5; // 상수 값

		// List 로 받아올텐데, type 은 Integer 로 하여 나열하는 Constructor add
		// getPaginationBarNumbers : Pagination Constructor
		// currentPageNumber : 최근 페이지
		// totalPages : 총 페이지 수
		public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages) {
				// int type field add => startNumber, endNumber
				// Math.max 함수를 이용하여, 인자 값이 0 보다 큰 값을 반환해주는 작업!
				// Math.min 함수를 이용하여, 인자 값이 totalPages 보다 작은 값을 반환해주는 작업!
				int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
				int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);

				// 현재 페이지 수 + 총 페이지 수 반환해주는 작업
				// IntStream.range : Integer Data Type 범위에 맞게 startNumber, endNumber 를 출력해 주는데?
				// boxed method 에서는 int Element 를 Integer Element 로 stream 상태를 생성 해주는 것이고?
				// toList 는 List 상태로 값을 변환해서 넘겨주겠다는 의미임!
				// [정리] => "Integer 상태로 startNumber, endNumber 를 이용하겠다는 의미임"
				return IntStream.range(startNumber, endNumber).boxed().toList();
		}

		// getter type 으로 pagination 현재 길이 상태를 알 수 있도록 작업
		public int currentBarLength() {
				return BAR_LENGTH;
		}
}