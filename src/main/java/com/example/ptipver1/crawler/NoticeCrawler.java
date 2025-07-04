package com.example.ptipver1.crawler;

import com.example.ptipver1.dto.NoticeDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
            Elements items = doc.select(".contents"); //이 부분을 어떤 태그로 넣어야할지 모루겠어요,,

            for (Element element : items) {
                String title = element.text();
                String content = element.text();
                System.out.println(title);
                System.out.println(content);
                notices.add(new NoticeDto(null, title, content));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notices;
    }
}
