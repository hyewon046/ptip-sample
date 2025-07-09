package com.example.ptipver1.service;

import com.example.ptipver1.summarize.AIApi;
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

    // 크롤링 및 저장
    public void fetchAndSave() {
        List<NoticeRaw> raws = crawler.fetchNotices();
        for (NoticeRaw raw : raws) {
            if (repository.findByOriginId(raw.originId()).isEmpty()) {
                String summary = AIApi.summarize(raw.content()); // 요약 API 호출
                Notice notice = Notice.from(raw, summary);
                repository.save(notice);
            }
        }
    }

    public List<NoticeDto> getAll() {
        return repository.findAll()
                .stream()
                .map(NoticeDto::from)
                .toList();
    }

    public String getSummaryById(Long id) {
        return repository.findById(id)
                .map(Notice::getSummary)
                .orElse("(요약 없음)");
    }
}
