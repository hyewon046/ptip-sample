package com.example.ptipver1.controller;

import com.example.ptipver1.repository.NoticeRepository;
import com.example.ptipver1.summarize.GeminiSummarizer;
import com.example.ptipver1.dto.Notice;
import com.example.ptipver1.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    @Autowired
    private NoticeRepository noticeRepository;

    // 전체 공지 리스트 조회
    @GetMapping
    public List<Notice> getAllNotices() {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    // 개별 공지 요약만 조회 (요약만 따로 쓰고 싶을 경우)
    @GetMapping("/{id}/summary")
    public String getSummary(@PathVariable Long id) {
        return noticeRepository.findById(id)
                .map(Notice::getSummary)
                .orElse("요약 정보 없음");
    }
}
