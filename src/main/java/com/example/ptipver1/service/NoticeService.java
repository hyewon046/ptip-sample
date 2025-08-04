package com.example.ptipver1.service;

import com.example.ptipver1.crawler.SiteCrawler;
import com.example.ptipver1.dto.Notice;
import com.example.ptipver1.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NoticeService {

    private final List<SiteCrawler> crawlers;
    private final NoticeRepository repository;

    public NoticeService(List<SiteCrawler> crawlers, NoticeRepository repository) {
        this.crawlers = crawlers;
        this.repository = repository;
    }

    public void UpdateAndSave() {
        Set<String> existingIds = repository.findAll().stream()
                .map(Notice::getOriginId)
                .collect(Collectors.toSet());

        for (SiteCrawler crawler : crawlers) {
            List<Notice> newNotices = crawler.getNewNotices(existingIds);
            repository.saveAll(newNotices);
        }
    }
}
