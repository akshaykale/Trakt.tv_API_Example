package com.akshaykale.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Akshay on 6/23/2016.
 */
public class MoviesListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Movie> mMovieList;


    public MoviesListAdapter(Context context, ArrayList<Movie> mMovieList) {
        this.context = context;
        this.mMovieList = mMovieList;
    }




    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.list_row_layout, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        }  else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        Movie movie = (Movie) getItem(position);

        //holder.countryName.setText(movie.getTitle());
        //holder.countryName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.earth, 0, 0, 0);

        return convertView;
    }

    private static class ViewHolder {
        private TextView title;
        private TextView year;

        public ViewHolder(View v) {
            //title = (TextView) v.findViewById(R.id.country_name);
            //year = (TextView) v.findViewById(R.id.);
        }
    }
}
