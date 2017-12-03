package com.example.adityasatalkar.newsapi.Model;

import android.graphics.drawable.Icon;

import java.util.List;

/**
 * Created by adityasatalkar on 12/2/17.
 */

public class IconBetterIdea {
    private String url;

    private List<Icon> icons;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Icon> getIcons() {
        return icons;
    }

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }
}
