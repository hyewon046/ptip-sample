package com.example.ptipver1.controller;

import com.example.ptipver1.dto.NoticeDto;
import com.example.ptipver1.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    private  NoticeService noticeService;

    @GetMapping("/notices")
    public String showNotices(Model model) {
        List<NoticeDto> notices = noticeService.getNotices();
        model.addAttribute("notices", notices);
        return "notices";
    }
}
