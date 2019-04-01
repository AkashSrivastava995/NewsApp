package com.example.newsing;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Extractor {
    private String Json = "";
    ArrayList<news> newsArrayList = new ArrayList<>();

    public Extractor(String jsonResponse) {
        Json = jsonResponse;
        extractData();
    }
    private void extractData() {
        try {
            JSONObject root = new JSONObject(Json);
            //Log.e("Extractor root", root.toString());
            JSONArray articles = root.getJSONArray("articles");
            //Log.e("Extractor num art", Integer.toString(articles.length()));

            for (int i = 0; i < articles.length(); i++) {
                JSONObject currentArticle = articles.getJSONObject(i);
                String title = currentArticle.getString("title");
                String des = currentArticle.getString("description");

                news n1 = new news(title,des);
                newsArrayList.add(n1);
            }
        }catch (JSONException e){
            //Show some message here.
        }
    }
    public ArrayList<news> getNewsArrayList() {
        return newsArrayList;
    }
}

