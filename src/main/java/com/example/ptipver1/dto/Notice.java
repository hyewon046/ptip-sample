package com.example.ptipver1.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(indexes = @Index(columnList = "originId", unique = true))
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originId;  // 크롤링된 게시글 PK
    private String title;

    @Column(length = 4000)
    private String summary;

    private String url;
    private LocalDateTime createdAt;

    public Notice(String originId, String title, String summary, String url, LocalDateTime createdAt) {
        this.originId = originId;
        this.title = title;
        this.summary = summary;
        this.url = url;
        this.createdAt = createdAt;
    }

}
