package com.example.ptipver1.crawler;

import com.example.ptipver1.dto.Notice;
import com.example.ptipver1.summarize.GeminiSummarizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public abstract class Crawler implements SiteCrawler {

    //카테고리 별로 baseurl이 달라서 구분
    protected abstract String getBaseUrl();
    protected abstract List<String> getOriginIdList(); // 검사할 게시글 ID 목록
    protected abstract String getBoardName(); // for log or debugging

    // 기존에 있던 공지 제외하고 새로운 공지 db에 저장
    @Override
    public List<Notice> getNewNotices(Set<String> existingOriginIds) {
        List<Notice> result = new ArrayList<>();

        for (String originId : getOriginIdList()) {
            if (existingOriginIds.contains(originId)) continue;

            try {
                String link = getBaseUrl() + originId + "/articleView.do?layout=unknown";
                Document doc = Jsoup.connect(link).get();

                Element titleEl = doc.selectFirst(".view-title");
                Element contentEl = doc.selectFirst(".view-con");

                if (titleEl == null || contentEl == null) continue;

                String title = titleEl.text().trim();
                String content = contentEl.html().trim();

                String summary = GeminiSummarizer.summarize(content);

                Notice notice = new Notice(null, originId, title, summary, link, LocalDateTime.now());
                result.add(notice);

            } catch (Exception e) {
                System.err.printf("[%s 크롤링 실패] originId: %s\n", getBoardName(), originId);
            }
        }

        return result;
    }
}
