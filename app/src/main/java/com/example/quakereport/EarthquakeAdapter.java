package com.example.quakereport;

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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

   public EarthquakeAdapter(Context context, ArrayList<Earthquake> quakeList){
      super(context, 0, quakeList);
   }

   @NonNull
   @Override
   public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View listItemView = convertView;
      if(listItemView == null){
         listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
      }
      Earthquake earthquake = getItem(position);

      TextView magnitudeTextView = listItemView.findViewById(R.id.magnitude_text_view);
      magnitudeTextView.setText(formatMagnitude(earthquake.getMagnitude()));

      GradientDrawable magnitudeCircle = (GradientDrawable)magnitudeTextView.getBackground();
      magnitudeCircle.setColor(getMagnitudeColor(earthquake.getMagnitude()));

      String[] locationData = formatLocation(earthquake.getLocation());

      TextView locationOffsetTextView = listItemView.findViewById(R.id.location_offset_text_view);
      locationOffsetTextView.setText(locationData[0]);

      TextView primaryLocationTextView = listItemView.findViewById(R.id.primary_location_text_view);
      primaryLocationTextView.setText(locationData[1]);

      TextView dateTextView = listItemView.findViewById(R.id.date_text_view);
      dateTextView.setText(formatDate(earthquake.getTimeInMilliseconds()));

      TextView timeTextView = listItemView.findViewById(R.id.time_text_view);
      timeTextView.setText(formatTime(earthquake.getTimeInMilliseconds()));

      return listItemView;
   }

   private String formatMagnitude(double magnitude) {
      DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
      return magnitudeFormat.format(magnitude);
   }

   private int getMagnitudeColor(double magnitude) {
      int magnitudeColorResourceId;
      switch ((int)Math.floor(magnitude)) {
         case 0:
         case 1:
            magnitudeColorResourceId = R.color.magnitude1;
            break;
         case 2:
            magnitudeColorResourceId = R.color.magnitude2;
            break;
         case 3:
            magnitudeColorResourceId = R.color.magnitude3;
            break;
         case 4:
            magnitudeColorResourceId = R.color.magnitude4;
            break;
         case 5:
            magnitudeColorResourceId = R.color.magnitude5;
            break;
         case 6:
            magnitudeColorResourceId = R.color.magnitude6;
            break;
         case 7:
            magnitudeColorResourceId = R.color.magnitude7;
            break;
         case 8:
            magnitudeColorResourceId = R.color.magnitude8;
            break;
         case 9:
            magnitudeColorResourceId = R.color.magnitude9;
            break;
         default:
            magnitudeColorResourceId = R.color.magnitude10plus;
            break;
      }
      return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
   }

   private String[] formatLocation(String location){
      String[] locationArray = new String[2];
      if(location.indexOf(" of ") > -1 && location.indexOf(" of ") + 4 < location.length()){
         locationArray[0] = location.substring(0, location.indexOf(" of ") + 4);
         locationArray[1] = location.substring(location.indexOf(" of ") + 4);
      }else{
         locationArray[0] = "Near the";
         locationArray[1] = location;
      }
      return locationArray;
   }

   private String formatDate(long time){
      SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
      return dateFormat.format(time);
   }

   private String formatTime(long time){
      SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
      return timeFormat.format(time);
   }
}
