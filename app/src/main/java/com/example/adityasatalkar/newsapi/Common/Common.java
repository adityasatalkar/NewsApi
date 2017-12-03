package com.example.adityasatalkar.newsapi.Common;

import com.example.adityasatalkar.newsapi.Interface.IconBetterIdeaService;
import com.example.adityasatalkar.newsapi.Interface.NewsService;
import com.example.adityasatalkar.newsapi.Remote.IconBetterIdeaClient;
import com.example.adityasatalkar.newsapi.Remote.RetrofitClient;

/**
 * Created by adityasatalkar on 12/2/17.
 */

public class Common {
    private static final String BASE_URL = "https://newsapi.org/";

    public static final String API_KEY = "e522eef50d144c43957cd170a1f5b614";

    public static NewsService getNewsService() {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

    public static IconBetterIdeaService getIconService() {
        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }
}