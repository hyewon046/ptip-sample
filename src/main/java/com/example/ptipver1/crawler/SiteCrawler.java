package com.example.ptipver1.crawler;

import com.example.ptipver1.dto.Notice;

import java.util.List;
import java.util.Set;

public interface SiteCrawler {
    List<Notice> getNewNotices(Set<String> existingOriginIds);
}
