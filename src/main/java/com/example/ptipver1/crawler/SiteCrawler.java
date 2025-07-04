package com.example.ptipver1.crawler;

import com.example.ptipver1.dto.NoticeDto;

import java.util.List;

public interface SiteCrawler {
    List<NoticeDto> fetchNotices();
}
