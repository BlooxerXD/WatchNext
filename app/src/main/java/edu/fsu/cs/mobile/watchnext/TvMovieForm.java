package edu.fsu.cs.mobile.watchnext;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TvMovieForm extends Fragment {
    TextView nameView,descriptionView,avaiabilityView,watchlistnameView,watchlistnameText,typeView,imbdView,notesView;
    EditText nameText,descriptionText,avalText,typeText,imbdText, notesText;
    String name, description, availability, wName, type, imbd, notes;
    Button submit,search_btn;
    Contract myContract;
    Spinner search;

    public TvMovieForm(){}



    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_movie_form, container, false);
        myContract= new Contract();

        wName = getArguments().getString("wName");

        nameView = (TextView) view.findViewById(R.id.Tv_Movie_Name);
        nameText = (EditText) view.findViewById(R.id.tv_movie_name_text);

        descriptionView = (TextView) view.findViewById(R.id.Description);
        descriptionText = (EditText) view.findViewById(R.id.description_text);

        avaiabilityView = (TextView) view.findViewById(R.id.availability);
        avalText = (EditText) view.findViewById(R.id.location);

        watchlistnameView = (TextView) view.findViewById(R.id.watchlist_name_movie);
        watchlistnameText = (TextView) view.findViewById(R.id.watchList_name_display);
        watchlistnameText.setText(wName);

        typeView = (TextView) view.findViewById(R.id.type);
        typeText = (EditText) view.findViewById(R.id.movietvtype_text);

        imbdView = (TextView) view.findViewById(R.id.imbd);
        imbdText = (EditText) view.findViewById(R.id.link_text);

        notesView = (TextView) view.findViewById(R.id.Notes);
        notesText = (EditText) view.findViewById(R.id.notes_text);

        submit = (Button)view.findViewById(R.id.sub);

        search = (Spinner)view.findViewById(R.id.spinner2);
        search_btn = (Button)view.findViewById(R.id.search_btn);

        search.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          name = nameText.getText().toString();
                                          try {
                                              JSONArray array = IMDBapi.SearchTitle(name);
                                              ArrayList<String> titles = new ArrayList<String>();
                                              titles.add("Select Searched Title");
                                              for (int i = 0; i < array.length(); i++) {
                                                  JSONObject object     = array.getJSONObject(i);
                                                  titles.add(object.getString("Title"));
                                              }

                                              ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,titles);
                                              // Specify the layout to use when the list of choices appears
                                              adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
                                              // Apply the adapter to the spinner
                                              search.setAdapter(adapter);

                                          } catch (IOException | JSONException e) {
                                              e.printStackTrace();
                                          }
                                      }
                                  }
        );



//        View.OnTouchListener spinnerOnTouch = new View.OnTouchListener() {
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    //Your code
//                    name = nameText.getText().toString();
//                    try {
//                        JSONArray array = IMDBapi.SearchTitle(name);
//                        ArrayList<String> titles = new ArrayList<String>();
//                        titles.add("Select Searched Title");
//                        for (int i = 0; i < array.length(); i++) {
//                            JSONObject object     = array.getJSONObject(i);
//                            titles.add(object.getString("Title"));
//                        }
//
//                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,titles);
//                        // Specify the layout to use when the list of choices appears
//                        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
//                        // Apply the adapter to the spinner
//                        search.setAdapter(adapter);
//                        return true;
//
//                    } catch (IOException | JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//                return false;
//            }
//        };
//
//        search.setOnTouchListener(spinnerOnTouch);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = true;
                name = nameText.getText().toString();
                description = descriptionText.getText().toString();
                availability = avalText.getText().toString();
//                wName = watchlistnameText.getText().toString();
                type = typeText.getText().toString();
                imbd = imbdText.getText().toString();
                notes = notesText.getText().toString();

                if (name == null) {
                    nameView.setTextColor(Color.RED);
                    name = "";
                    flag = false;
                }

                if (description == null) {
                    flag = false;
                }

                if (availability == null) {
                    flag = false;
                }

                if (type == null) {
                    flag = false;
                }


                if (imbd == null) {
                    flag = false;
                }


                if (notes == null) {
                    flag = false;
                }

                if(flag){
                    myContract.addNewMovie(getActivity(),wName,name,description,availability,imbd,notes,type);
                    Intent intent = new Intent(getActivity(), TvMovieMain.class);

                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(), "Please enter correct information to add a new movie.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}