package com.example.ptipver1.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(indexes = @Index(columnList = "originId", unique = true))
@AllArgsConstructor
public class Notice {
    @Id
    @GeneratedValue
    private Long id;
    private String originId;  // 크롤링된 게시글 PK
    private String title;

    @Column(length = 4000)
    private String summary;

    private String link;
    private LocalDateTime createdAt;

    // 정적 팩토리 메서드
    public static Notice from(NoticeRaw raw, String summary) {
        Notice notice = new Notice();
        notice.originId = raw.originId();
        notice.title = raw.title();
        notice.link = raw.link();
        notice.summary = summary;
        notice.createdAt = LocalDateTime.now();
        return notice;
    }

}
