package com.example.ptipver1.service;

import com.example.ptipver1.summarize.GeminiSummarizer;
import com.example.ptipver1.crawler.SiteCrawler;
import com.example.ptipver1.dto.Notice;
import com.example.ptipver1.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private final SiteCrawler crawler;
    private final NoticeRepository repository;

    public NoticeService(SiteCrawler crawler, NoticeRepository repository) {
        this.crawler = crawler;
        this.repository = repository;
    }

    public void fetchAndSave() {
        List<Notice> notices = crawler.fetchNotices();
        for (Notice notice : notices) {
            if (repository.findByOriginId(notice.getOriginId()).isEmpty()) {
                repository.save(notice);
            }
        }
    }

    public List<Notice> getNotices() {
        return repository.findAll();
    }

    public String getSummaryById(Long id) {
        return repository.findById(id)
                .map(Notice::getSummary)
                .orElse("(요약 없음)");
    }
}
