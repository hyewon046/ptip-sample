package com.example.ptipver1.crawler;

import com.example.ptipver1.dto.Notice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoticeCrawler implements SiteCrawler {

    @Override
    public List<NoticeRaw> fetchNotices() {
        List<NoticeRaw> list = new ArrayList<>();
        try {
            String originId = "32031"; // 게시글 PK
            String link = "https://www.ptu.ac.kr/bbs/www/310/32031/artclView.do";
            Document doc = Jsoup.connect(link).get();

            String title = doc.selectFirst(".view-title").text().trim();
            String content = doc.selectFirst(".view-con").html();

            // HTML 태그 정리
            content = content.replaceAll("(?i)<br\\s*/?>", "\n")
                    .replaceAll("(?i)</p>", "\n")
                    .replaceAll("&nbsp;", " ")
                    .replaceAll("<[^>]*>", "")
                    .trim();

            list.add(new NoticeRaw(originId, title, content, link));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
