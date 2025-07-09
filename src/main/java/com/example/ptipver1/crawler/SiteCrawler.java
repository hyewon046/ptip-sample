package com.example.ptipver1.crawler;

import com.example.ptipver1.dto.Notice;

import java.util.List;

public interface SiteCrawler {
    List<Notice> fetchNotices();
}
