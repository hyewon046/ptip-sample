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
            Document doc = Jsoup.connect("https://www.ptu.ac.kr/bbs/www/310/32031/artclView.do").get(); //32031은 게시글 PK
            System.out.println(doc.title());

            //제목 가져오기
            Element titleElement = doc.selectFirst(".view-title");
            String title = titleElement != null ? titleElement.text().trim() : "(제목 없음)";

            //본문가져오기
            Element contentElement = doc.selectFirst(".view-con");
            String originContent = contentElement != null ? contentElement.html() : "";
            String content = originContent
                    .replaceAll("(?i)<br\\s*/?>", "\n")
                    .replaceAll("(?i)</p>", "\n")
                    .replaceAll("&nbsp;", " ")
                    .replaceAll("<[^>]*>", "")
                    .trim()
                    .replace("\n", "<br>");

            System.out.println("제목: " + title);
            System.out.println("내용: " + content);

            notices.add(new NoticeDto(null, title, content));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notices;
    }
}
