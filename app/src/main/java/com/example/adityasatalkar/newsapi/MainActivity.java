package com.example.adityasatalkar.newsapi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adityasatalkar.newsapi.Adapter.ListSourceAdapter;
import com.example.adityasatalkar.newsapi.Common.Common;
import com.example.adityasatalkar.newsapi.Interface.NewsService;
import com.example.adityasatalkar.newsapi.Model.WebSite;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView listWebSite;
    RecyclerView.LayoutManager layoutManager;
    NewsService mService;
    ListSourceAdapter adapter;
    AlertDialog dialog;
    SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init Cache
        Paper.init(this);

        //Init Service
        mService = Common.getNewsService();

        //Init View
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebsiteSource(true);
            }
        });

        listWebSite = (RecyclerView) findViewById(R.id.list_source);
        listWebSite.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listWebSite.setLayoutManager(layoutManager);

        dialog = new SpotsDialog(this);

        loadWebsiteSource(false);
    }

    private void loadWebsiteSource(boolean isRefreshed) {

        if(!isRefreshed) {
            String cache = Paper.book().read("cache");

            //If Cache Present
            if(cache != null && !cache.isEmpty())
            {
                WebSite webSite = new Gson().fromJson(cache, WebSite.class);        //Convert Cache from Json to Object
                adapter = new ListSourceAdapter(getBaseContext(),webSite);
                adapter.notifyDataSetChanged();
                listWebSite.setAdapter(adapter);
            }
            else // If cache not present
            {
                dialog.show();

                //Fetch New Data

                mService.getSources().enqueue(new Callback<WebSite>() {
                    @Override
                    public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                        adapter = new ListSourceAdapter(getBaseContext(),response.body());
                        adapter.notifyDataSetChanged();
                        listWebSite.setAdapter(adapter);

                        //Save to Cache
                        Paper.book().write("cache", new Gson().toJson(response.body()));
                    }

                    @Override
                    public void onFailure(Call<WebSite> call, Throwable t) {

                    }
                });
            }
        }
        else    //If from Swipe to Refresh
        {
            dialog.show();

            //Fetch New Data

            mService.getSources().enqueue(new Callback<WebSite>() {
                @Override
                public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                    adapter = new ListSourceAdapter(getBaseContext(),response.body());
                    adapter.notifyDataSetChanged();
                    listWebSite.setAdapter(adapter);

                    //Save to Cache
                    Paper.book().write("cache", new Gson().toJson(response.body()));

                    //Dismiss refresh progressing
                    swipeLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<WebSite> call, Throwable t) {

                }
            });

        }
    }
}
