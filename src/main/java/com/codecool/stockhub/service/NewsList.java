package com.codecool.stockhub.service;

import com.codecool.stockhub.logger.ExceptionLog;
import com.codecool.stockhub.model.News;
import com.codecool.stockhub.repository.NewsRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsList {

    @Autowired
    private NewsRepository newsRepository;

    private final ExceptionLog exceptionLog = new ExceptionLog();

    public void getData(String content) {

        try {
            JSONArray jsonContent = new JSONArray(content);

            for (int i = 0; i < jsonContent.length(); i++) {

                JSONObject currentObj = jsonContent.getJSONObject(i);

                News news = News.builder()
                        .headline(currentObj.getString("headline"))
                        .category(currentObj.getString("category"))
                        .image(currentObj.getString("image"))
                        .datetime(currentObj.getString("datetime"))
                        .source(currentObj.getString("source"))
                        .url(currentObj.getString("url"))
                        .build();

                newsRepository.save(news);
            }

            newsRepository.flush();

        } catch (JSONException e) {

            exceptionLog.log(e);
            throw new IllegalArgumentException("Content format is not valid");
        }
    }
}
