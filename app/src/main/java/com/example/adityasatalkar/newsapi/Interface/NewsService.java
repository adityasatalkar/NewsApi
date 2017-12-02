package com.example.adityasatalkar.newsapi.Interface;

import com.example.adityasatalkar.newsapi.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by adityasatalkar on 12/2/17.
 */

public interface NewsService {

    @GET("v2/sources?language=en")
    Call<WebSite> getSources();
}
