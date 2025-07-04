package com.example.ptipver1.crawler;

import com.example.ptipver1.dto.NoticeDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoticeCrawler implements SiteCrawler {

    @Override
    public List<NoticeDto> fetchNotices() {
        List<NoticeDto> notices = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.ptu.ac.kr/bbs/www/310/32031/artclView.do").get(); //32031부분이 pk
            System.out.println(doc.title());
            Elements items = doc.select(".notice-list li a"); //이 부분을 어떤 태그로 넣어야할지 모루겠어요,,,,,

            for (var element : items) {
                String url = element.absUrl("href");
                String title = element.text();
                String content = element.text();
                notices.add(new NoticeDto(url, title, content));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notices;
    }
}
