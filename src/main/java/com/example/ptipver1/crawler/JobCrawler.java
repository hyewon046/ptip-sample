package com.example.ptipver1.crawler;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class JobCrawler extends Crawler {

    @Override
    protected String getBaseUrl() {
        return "https://njob.ptu.ac.kr/bbs/njob/152/";
    }

    @Override
    protected List<String> getOriginIdList() {
        return IntStream.rangeClosed(28000, 28999)
                .mapToObj(String::valueOf)
                .toList();
    }

    @Override
    protected String getBoardName() {
        return "취업관련";
    }
}
