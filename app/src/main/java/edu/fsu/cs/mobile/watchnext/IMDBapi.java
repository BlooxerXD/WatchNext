package edu.fsu.cs.mobile.watchnext;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IMDBapi {
    public static String convertToAPI(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
    }

    static public JSONArray SearchTitle(String search)throws IOException{
        OkHttpClient client = new OkHttpClient();
        search = convertToAPI(search);


        Request request = new Request.Builder()
                .url("https://movie-database-imdb-alternative.p.rapidapi.com/?s="+search+"&page=1&r=json")
                .get()
                .addHeader("x-rapidapi-key", "7b6b9ec86cmshdf7cb8756356c67p1e126djsnb25c389a6f8a")
                .addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
                .build();


        Response response = client.newCall(request).execute();
        String responseStr = response.body().string();
        JSONObject Jobject = null;
        List<String> titles = new ArrayList<String>();

        try {
            Jobject = new JSONObject(responseStr);
            JSONArray Jarray = Jobject.getJSONArray("Search");
//            for (int i = 0; i < Jarray.length(); i++) {
//                JSONObject object     = Jarray.getJSONObject(i);
//                titles.add(object.getString("Title"));
//            }
            return Jarray;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    static public boolean TestRequest(String search) throws IOException {
        OkHttpClient client = new OkHttpClient();
        search = convertToAPI(search);


        Request request = new Request.Builder()
                .url("https://movie-database-imdb-alternative.p.rapidapi.com/?s="+search+"&page=1&r=json")
                .get()
                .addHeader("x-rapidapi-key", "7b6b9ec86cmshdf7cb8756356c67p1e126djsnb25c389a6f8a")
                .addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
                .build();


        Response response = client.newCall(request).execute();
        String responseStr = response.body().string();
        JSONObject Jobject = null;
        List<String> titles = new ArrayList<String>();

        try {
            Jobject = new JSONObject(responseStr);
            JSONArray Jarray = Jobject.getJSONArray("Search");
            for (int i = 0; i < Jarray.length(); i++) {
                JSONObject object     = Jarray.getJSONObject(i);
                titles.add(object.getString("Title"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        final List<String>[] t = new List[]{new ArrayList<String>()};
//
//
//        Callback callback = new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.v("RESPONSE","RESPONSE FAILED");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String responseStr = response.body().string();
//                    try {
//                        JSONObject Jobject = new JSONObject(responseStr);
//                        JSONArray Jarray = Jobject.getJSONArray("Search");
//                        List<String> titles = new ArrayList<String>();
//                        for (int i = 0; i < Jarray.length(); i++) {
//                            JSONObject object     = Jarray.getJSONObject(i);
//                            titles.add(object.getString("Title"));
//                        }
//                        t[0] = titles;
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    // Do what you want to do with the response.
//                    Log.v("RESPONSE",responseStr);
//                } else {
//                    Log.v("RESPONSE","RESPONSE FAILED");
//
//                    // Request not successful
//                }
//            }
//        };

       // call.enqueue(callback);



        return true;

    }


}
