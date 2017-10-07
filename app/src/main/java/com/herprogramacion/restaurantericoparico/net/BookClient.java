package com.herprogramacion.restaurantericoparico.net;

import android.util.Log;

import com.herprogramacion.restaurantericoparico.activities.BookListActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BookClient {
    private static final String API_BASE_URL = "http://ergast.com/api/f1/";
    private AsyncHttpClient client;

    public BookClient() {
        this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    // Method for accessing the search API
    public void getBooks(final String query, JsonHttpResponseHandler handler) {
        try {

            String url = "";
            if (query=="") {
                url = getApiUrl(BookListActivity.year+"/drivers");
            } else {
                url = getApiUrl(BookListActivity.year+"/drivers/");
            }

            Log.e("***oooo***",url);
            client.get(url + URLEncoder.encode(query, "utf-8"), handler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("catch","Catch");
        }
    }

}





