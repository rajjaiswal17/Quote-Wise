package com.example.hanutalks;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ArrayList<QuoteModel> ar = new ArrayList<>();
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ApiCall().execute();
    }


 class ApiCall extends AsyncTask<Void, Void, Void>
    {
        Response response;
        ProgressBar pb = findViewById(R.id.loading);

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String myUrl = "https://script.google.com/macros/s/AKfycbw8IshQk4tA6nzG69kXRChSKQTOB0UfAXrL43BfTef79Cv_OMgY4r5oG_B16Bh4PH9L/exec";
            Request request = new Request.Builder().url(myUrl).build();
            try {
                response = client.newCall(request).execute();
                if(response.body() != null)
                {
                    String sResponse = response.body().string();
                    JSONObject dataObj = new JSONObject(sResponse);
                    JSONArray dataArr = dataObj.getJSONArray("vani");

                    for(int i = 0; i<dataArr.length(); i++)
                    {
                        JSONObject quoteObj = dataArr.getJSONObject(i);
                        ar.add(new QuoteModel(quoteObj.getInt("id"), quoteObj.getString("data")));
                    }

                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {

            super.onPostExecute(unused);
            //setup recyclerview
            RecyclerView rv = findViewById(R.id.rcView);
            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            RecyclerQuoteAdapter adapter = new RecyclerQuoteAdapter(MainActivity.this, ar);
            rv.setAdapter(adapter);
            pb.setVisibility(View.GONE);

        }
    }
}

