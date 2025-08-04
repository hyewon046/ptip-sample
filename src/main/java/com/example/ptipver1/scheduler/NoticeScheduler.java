package com.example.ptipver1.scheduler;

import com.example.ptipver1.service.NoticeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NoticeScheduler {

    private final NoticeService noticeService;

    public NoticeScheduler(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Scheduled(cron = "0 0 0/1 * * *")
    public void runCrawler() {
        noticeService.UpdateAndSave();
        System.out.println("공지사항 자동 크롤링 및 요약 완료!");
    }
}