package com.example.adityasatalkar.newsapi.Interface;

import com.example.adityasatalkar.newsapi.Model.IconBetterIdea;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by adityasatalkar on 12/2/17.
 */

public interface IconBetterIdeaService {

    @GET
    Call<IconBetterIdea> getIconUrl(@Url String url);
}
