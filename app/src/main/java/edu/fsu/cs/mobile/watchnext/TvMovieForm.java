package edu.fsu.cs.mobile.watchnext;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class TvMovieForm extends Fragment {
    TextView nameView,descriptionView,avaiabilityView,watchlistnameView,watchlistnameText,typeView,imbdView,notesView;
    EditText nameText,descriptionText,avalText,typeText,imbdText, notesText;
    String name, description, availability, wName, type, imbd, notes;
    Button submit;
    Contract myContract = new Contract();

    public TvMovieForm(){}



    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_movie_form, container, false);

        nameView = (TextView) view.findViewById(R.id.Tv_Movie_Name);
        nameText = (EditText) view.findViewById(R.id.tv_movie_name_text);

        descriptionView = (TextView) view.findViewById(R.id.Description);
        descriptionText = (EditText) view.findViewById(R.id.description_text);

        avaiabilityView = (TextView) view.findViewById(R.id.availability);
        avalText = (EditText) view.findViewById(R.id.location);

        watchlistnameView = (TextView) view.findViewById(R.id.watchlist_name_movie);
        watchlistnameText = (TextView) view.findViewById(R.id.watchList_name_display);

        typeView = (TextView) view.findViewById(R.id.type);
        typeText = (EditText) view.findViewById(R.id.movietvtype_text);

        imbdView = (TextView) view.findViewById(R.id.imbd);
        imbdText = (EditText) view.findViewById(R.id.link_text);

        notesView = (TextView) view.findViewById(R.id.Notes);
        notesText = (EditText) view.findViewById(R.id.notes_text);

        submit = (Button)view.findViewById(R.id.sub);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;
                name = nameText.getText().toString();
                description = descriptionText.getText().toString();
                availability = avalText.getText().toString();
                wName = watchlistnameText.getText().toString();
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
                }
                else{
                    Toast.makeText(getActivity(), "Please enter correct information to add a new movie.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}