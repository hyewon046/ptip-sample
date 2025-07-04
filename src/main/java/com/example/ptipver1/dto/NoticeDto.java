package com.example.ptipver1.dto;

public class NoticeDto {

    private String url;
    private String title;
    private String content;

    public NoticeDto(String url, String title, String content) {
        this.url = url;
        this.title = title;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
