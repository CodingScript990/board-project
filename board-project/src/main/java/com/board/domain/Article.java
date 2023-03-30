package com.board.domain;

import java.time.LocalDateTime;

// Article Class
public class Article {
    // Filed add => id, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy
    private Long id; // PK
    private String title; // varchar[제목]
    private String content; // varchar[본문]
    private String hashtag; // varchar[해시태그]
    private LocalDateTime createdAt; // datetime[생성일시]
    private String createdBy; // varchar[생성자]
    private LocalDateTime modifiedAt; // datetime[수정일시]
    private String modifiedBy; // varchar[수정자]
}
