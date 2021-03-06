package edu.fsu.cs.mobile.watchnext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class TvMovieActivity extends AppCompatActivity {
    public static ListView listView;
    public static List<String> TvMovieListNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_movie);
        String wName = getIntent().getStringExtra("wName");

        Bundle bundle = new Bundle();
        bundle.putString("wName",wName);
        TvMovieForm tvMovieForm = new TvMovieForm();
        tvMovieForm.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, tvMovieForm );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




}