package edu.fsu.cs.mobile.watchnext;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

import static android.graphics.BlendMode.COLOR;


public class TvMovieForm extends Fragment {
    TextView nameView,avaiabilityView,watchlistnameView,watchlistnameText;
    EditText nameText;
    Spinner avalText;
    String name, availability, wName, type, notes;
    Button submit,search_btn;
    Contract myContract;
    Spinner search;
    JSONArray search_array;
    ArrayList<String> titles;
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


        avaiabilityView = (TextView) view.findViewById(R.id.availability);
        avalText = (Spinner) view.findViewById(R.id.spinner_ava);



        watchlistnameView = (TextView) view.findViewById(R.id.watchlist_name_movie);
        watchlistnameText = (TextView) view.findViewById(R.id.watchList_name_display);
        watchlistnameText.setText(wName);




        submit = (Button)view.findViewById(R.id.sub);

        search = (Spinner)view.findViewById(R.id.spinner2);
        search_btn = (Button)view.findViewById(R.id.search_btn);
        titles = new ArrayList<String>();

        search_btn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          name = nameText.getText().toString();
                                          try {
                                              search_array = IMDBapi.SearchTitle(name);
                                              titles.clear();
                                              if(search_array !=null) {

                                                  titles.add("Select Searched Title");
                                                  for (int i = 0; i < search_array.length(); i++) {
                                                      JSONObject object = search_array.getJSONObject(i);
                                                      titles.add(object.getString("Title"));
                                                  }
                                              }else{


                                                  titles.add("No Search terms Found");

                                              }

                                                  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, titles);
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



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean flag = true;
                name = search.getSelectedItem().toString();
                availability = avalText.getSelectedItem().toString();
                notes = "";

                String imdbnum = "";

                if (name == null) {
                    nameView.setTextColor(Color.RED);
                    name = "";
                    flag = false;
                }



                if (availability == null) {

                    flag = false;
                }


//                if (notes == null) {
//                    notesView.setTextColor(Color.RED);
//                    flag = false;
//                }

                if(search == null || search.getSelectedItem().toString().equals("Select Searched Title") ||search.getSelectedItem().toString().equals("No Search terms Found")){
                    flag = false;
                }else{
                    try {
                        imdbnum = search_array.getJSONObject(search.getSelectedItemPosition()-1).getString("imdbID");
                        IMDBapi.GetIMDBEntry(imdbnum);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }


                if(flag){
                    myContract.addNewMovie(getActivity(),wName,name,availability,imdbnum,notes);
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