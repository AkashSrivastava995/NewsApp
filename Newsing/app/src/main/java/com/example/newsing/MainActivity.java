package com.example.newsing;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<news> newsArrayList = new ArrayList<>();
    ListView listView;
    newsAdapter adapter;
    ProgressBar progressBar;
    final String url= "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=d7a1a407cfeb49d2ac9c882de38c08c3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsAsyncTask task = new newsAsyncTask();
        task.execute();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listView = (ListView) findViewById(R.id.newsListView);
    }

    private class newsAsyncTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... voids) {
            String jsonResponse= "";

            try {
                jsonResponse = makeHttpRequest(url);
            }
            catch (Exception e){
                // Do something here
            }

            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            Extractor extrator = new Extractor(s);
            progressBar.setVisibility(View.INVISIBLE);
            newsArrayList = extrator.getNewsArrayList();
            adapter = new newsAdapter(getApplicationContext(),newsArrayList);
            listView.setAdapter(adapter);
            super.onPostExecute(s);
        }

        private String makeHttpRequest(String stringUrl) {

            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
            }

            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
    }
}
