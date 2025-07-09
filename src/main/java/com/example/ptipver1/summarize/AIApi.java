package com.example.ptipver1.summarize;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class AIApi {
    public static String summarize(String content) {
        try {
            String prompt = "다음 내용을 5줄 이내로 요약해줘:\n" + content;

            Client client = new Client();
            GenerateContentResponse response = client.models.generateContent(
                    "gemini-2.5-flash",
                    prompt,
                    null
            );

            return response.text();
        } catch (Exception e) {
            e.printStackTrace();
            return "(요약 실패)";
        }
    }

}
