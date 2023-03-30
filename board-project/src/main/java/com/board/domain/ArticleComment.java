package com.board.domain;

import java.time.LocalDateTime;

// ArticleComment Class
public class ArticleComment {
    // Field add => id, article_id, content, createdAt, createdBy, modifiedAt, modifiedBy
    private Long id; // PK
    private Article article; // FK[게시글 ID]
    private String content; // varchar[본문]
    private LocalDateTime createdAt; // datetime[생성일시]
    private String createdBy; // varchar[생성자]
    private LocalDateTime modifiedAt; // datetime[수정일시]
    private String modifiedBy; // varchar[수정자]
}
