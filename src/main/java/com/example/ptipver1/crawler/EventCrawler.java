package com.example.ptipver1.crawler;

import java.util.List;
import java.util.stream.IntStream;

public class EventCrawler extends Crawler{
    @Override
    protected String getBaseUrl() {
        return "https://www.ptu.ac.kr/bbs/www/318/";
    }

    @Override
    protected List<String> getOriginIdList() {
        return IntStream.rangeClosed(32000, 32999)
                .mapToObj(String::valueOf)
                .toList();
    }

    @Override
    protected String getBoardName() {
        return "행사관련";
    }
}
