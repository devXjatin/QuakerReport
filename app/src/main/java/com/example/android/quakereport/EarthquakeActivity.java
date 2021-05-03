package com.example.android.quakereport;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        //call the QueryUtils method
       final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();


        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        //Set intent when we click it then we gone to the page
        earthquakeListView.setOnItemClickListener((parent, view, position, id) -> {
            //find the earthquake that was clicked on
            Earthquake currentEarthquake = adapter.getItem(position);

            //Convert the String URL into URI object (to pass into the intent constructor)
            Uri earthquakeUrl = Uri.parse(currentEarthquake.getUrl());

            //Create a new intent to view earthquake url
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUrl );

            //Send the intent to launch a new Activity
           startActivity(websiteIntent);

        });


    }
}