package com.example.android.quakereport;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<Earthquake> {

    public CustomArrayAdapter(Context context, List<Earthquake>
            earthquakeActivityArraysList) {
        super(context, 0, earthquakeActivityArraysList);

    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,
                    parent, false);
        }

        Earthquake currentEarthQuake = getItem(position);

        //set magnitude and Format tha magnitude upto 1 decimal place
        DecimalFormat formatter = new DecimalFormat("0.0");
        String formattedMagnitude = formatter.format
                (Double.parseDouble(currentEarthQuake.getMagnitude()));
        TextView magnitudeText = (TextView) listItemView.findViewById(R.id.textViewMagnitude);
        magnitudeText.setText(formattedMagnitude);

        //Set proper background color on the magnitude circle
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeText.getBackground();
        int magnitudeColor = getMagnitudeColor(
                Double.parseDouble(currentEarthQuake.getMagnitude()));
        magnitudeCircle.setColor(magnitudeColor);


        //set location and offset
        String locationOffset, primaryLocation;
        String originalLocation = currentEarthQuake.getLocation();
        if(originalLocation.contains("of")){
            String[] parts = originalLocation.split("of");
            locationOffset = parts[0] + "of";
            primaryLocation = parts[1];
        }
        else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        //offset
        TextView offSetView = (TextView) listItemView.findViewById(R.id.offSetView);
        offSetView.setText(locationOffset);
        //location
        TextView locationText = (TextView) listItemView.findViewById(R.id.textViewLocation);
        locationText.setText(primaryLocation);

        //set date
        Date dateOfObject = new Date(currentEarthQuake.getMilliSeconds());
        TextView dateText = (TextView) listItemView.findViewById(R.id.textViewDate);
        String formatDate = formatDate(dateOfObject);
        dateText.setText(formatDate);

        //set time
        TextView timeText = (TextView) listItemView.findViewById(R.id.timeView);
        String timeFormat = formatTime(dateOfObject);
        timeText.setText(timeFormat);

        return listItemView;
    }
    private int getMagnitudeColor(Double magnitude){
        int magnitudeResourceId=0;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
                break;
            case 1: magnitudeResourceId = R.color.magnitude1;
                     break;
            case 2: magnitudeResourceId = R.color.magnitude2;
                    break;
            case 3: magnitudeResourceId = R.color.magnitude3;
                break;
            case 4: magnitudeResourceId = R.color.magnitude4;
                break;
            case 5: magnitudeResourceId = R.color.magnitude5;
                break;
            case 6: magnitudeResourceId = R.color.magnitude6;
                break;
            case 7: magnitudeResourceId = R.color.magnitude7;
                break;
            case 8: magnitudeResourceId = R.color.magnitude8;
                break;
            case 9: magnitudeResourceId = R.color.magnitude9;
                break;
            default: magnitudeResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeResourceId);
        

    }
    private String formatDate(Date dateOfObject){
     SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateOfObject);
    }

    private String formatTime(Date dateOfObject){
       SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(dateOfObject);
    }



}
