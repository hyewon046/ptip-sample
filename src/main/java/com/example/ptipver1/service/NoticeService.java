package com.example.ptipver1.service;

import com.example.ptipver1.crawler.SiteCrawler;
import com.example.ptipver1.dto.NoticeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    private final SiteCrawler crawler;

    public NoticeService(SiteCrawler crawler) {
        this.crawler = crawler;
    }

    public List<NoticeDto> getNotices() {
        return crawler.fetchNotices();
    }
}
