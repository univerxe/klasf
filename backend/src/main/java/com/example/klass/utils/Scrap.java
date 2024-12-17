package com.example.klass.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrap {
    public String translateText(String text, String targetLanguage) throws java.io.IOException {
        try {
            // Use Jsoup to fetch the translation from a free translation service
            String url = "https://translate.google.com/m?hl=en&sl=auto&tl=" + targetLanguage + "&q=" + text;
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div.result-container");
            if (elements != null && !elements.isEmpty()) {
                Element firstElement = elements.first();
                if (firstElement != null) {
                    return firstElement.text();
                } else {
                    return text; // Return original text if translation fails
                }
            } else {
                return text; // Return original text if translation fails
            }
        } catch (IOException e) {
            // Log the exception
            System.err.println("IOException occurred: " + e.getMessage());
            return text; // Return original text in case of an error
        } catch (IllegalArgumentException e) {
            // Log the exception
            System.err.println("IllegalArgumentException occurred: " + e.getMessage());
            return text; // Return original text in case of an error
        }
    }


    public static ArrayList<HashMap<String, String>> fetchNews() throws java.io.IOException {
        String url = "https://www.kw.ac.kr/ko/life/notice.jsp?srCategoryId=10";
        ArrayList<HashMap<String, String>> resuList = new ArrayList<>();
        try {
            // Fetch the HTML content of the webpage
            Document doc = Jsoup.connect(url).get();

            // Select elements with the class 'board-text'
            Elements notices = doc.select(".board-text");

            for (Element notice : notices) {
                // Extract title
                String title = notice.select("a strong.category").text() + " " +
                               notice.select("a").text();
                title = title.replace("Attachment", "");
                title = title.replace("[국제학생]", "");

                // Translate title to English
                Scrap scrap = new Scrap();
                String translatedTitle = scrap.translateText(title, "en");
                System.out.println("Translated title: " + translatedTitle);
            

                // Extract link
                String link = "https://www.kw.ac.kr" + notice.select("a").attr("href");
                // Extract additional information (e.g., date, department)
                String info = notice.select("p.info").text();

                // Print notice details
                HashMap<String, String> noticeMap = new HashMap<>();
                noticeMap.put("title", translatedTitle);
                noticeMap.put("link", link);
                noticeMap.put("info", info);
                resuList.add(noticeMap);
            }
            return resuList;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
