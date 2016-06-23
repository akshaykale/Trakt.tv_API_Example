package com.akshaykale.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        return MainActivity.mPopularMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.mPopularMovieList.get(position);
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
        //if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.list_row_normal, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        //}  else {
            // if holder created, get tag from view
          //  holder = (ViewHolder) convertView.getTag();
        //}

        Movie movie = (Movie) getItem(position);

        holder.title.setText(movie.getTitle());
        holder.year.setText(""+movie.getYear());
        holder.rating.setText(String.valueOf(movie.getRating()).substring(0,3));
        String gener = movie.getGenres();
        if(gener!=null && !gener.equals("")){
            if (gener.contains("action"))
                holder.tv_action.setVisibility(View.VISIBLE);
            else
                holder.tv_action.setVisibility(View.GONE);
            if (gener.contains("adventure"))
                holder.tv_adventure.setVisibility(View.VISIBLE);
            if (gener.contains("fantasy"))
                holder.tv_fantancy.setVisibility(View.VISIBLE);
            if (gener.contains("science-fiction"))
                holder.tv_sience_fiction.setVisibility(View.VISIBLE);
            if (gener.contains("drama"))
                holder.tv_drama.setVisibility(View.VISIBLE);
            if (gener.contains("comedy"))
                holder.tv_comedy.setVisibility(View.VISIBLE);
            if (gener.contains("romance"))
                holder.tv_romance.setVisibility(View.VISIBLE);
        }

        try{
            Picasso.with(context)
                    .load(movie.getBanner()).resize(150, 150)
                    //.centerCrop()
                    .into(holder.banner);
        }catch (Exception e){ e.printStackTrace(); }
        return convertView;
    }

    private static class ViewHolder {
        private TextView title,year,rating;
        private ImageView banner;

        //tags genere
        private TextView tv_action,tv_comedy,tv_drama,tv_adventure,tv_sience_fiction,tv_romance,tv_fantancy;

        public ViewHolder(View v) {
            title = (TextView) v.findViewById(R.id.tv_row_title);
            year = (TextView) v.findViewById(R.id.tv_row_year);
            rating = (TextView) v.findViewById(R.id.tv_row_rating);
            banner = (ImageView) v.findViewById(R.id.iv_row_banner);
            tv_action = (TextView) v.findViewById(R.id.action);
            tv_comedy = (TextView) v.findViewById(R.id.comedy);
            tv_drama = (TextView) v.findViewById(R.id.drama);tv_adventure = (TextView) v.findViewById(R.id.adventure);
            tv_sience_fiction = (TextView) v.findViewById(R.id.sience_fiction);
            tv_romance = (TextView) v.findViewById(R.id.romance);
            tv_fantancy = (TextView) v.findViewById(R.id.fantasy);
        }
    }
}
