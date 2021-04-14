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
    public static final String TITLE = "Title";
    public static final String YEAR = "Year";
    public static final String RATED = "Rated";
    public static final String RELEASED = "Released";
    public static final String RUNTIME = "Runtime";
    public static final String GENRE = "Genre";
    public static final String DIRECTOR = "Director";

    public static final String PLOT = "Plot";
    public static final String IMDBRATING = "imdbRating";
    public static final String METASCORE = "Metascore";
    public static final String TYPE = "Type";
    public static final String MONEY = "BoxOffice";
    public static final String POSTER = "Poster";

    public static final String ERROR_STR = "Not Found";

    public static String getImdbrating(JSONObject imdbEntry) throws JSONException {
        try {
            return imdbEntry.getString(IMDBRATING);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return ERROR_STR;
    }

    public static String getPlot(JSONObject imdbEntry) throws JSONException {
        try {
            return imdbEntry.getString(PLOT);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return ERROR_STR;
    }

    public static String getType(JSONObject imdbEntry) throws JSONException {
        try {
            return imdbEntry.getString(TYPE);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return ERROR_STR;
    }

    public static String getDirector(JSONObject imdbEntry) throws JSONException {
        try {
            return imdbEntry.getString(DIRECTOR);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return ERROR_STR;
    }

    public static String getRating(JSONObject imdbEntry) throws JSONException {
        try {
            return imdbEntry.getString(RATED);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return ERROR_STR;
    }

    public static String getPoster(JSONObject imdbEntry) throws JSONException {
        try {
            return imdbEntry.getString(POSTER);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return ERROR_STR;
    }

    public static String convertToAPI(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
    }

    static public JSONObject GetIMDBEntry(String imdbID)throws IOException{
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://movie-database-imdb-alternative.p.rapidapi.com/?i="+imdbID+"&r=json")
                .get()
                .addHeader("x-rapidapi-key", "7b6b9ec86cmshdf7cb8756356c67p1e126djsnb25c389a6f8a")
                .addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        String responseStr = response.body().string();
        JSONObject Jobject = null;

        try {
            Jobject = new JSONObject(responseStr);
            return Jobject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;


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
        return true;

    }



}
