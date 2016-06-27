package com.akshaykale.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Akshay on 6/23/2016.
 */
public class SearchListAdapter extends BaseAdapter {

    Context context;


    public SearchListAdapter(Context context) {
        this.context = context;
    }




    @Override
    public int getCount() {
        return SearchActivity.mMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return SearchActivity.mMovieList.get(position);
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

        try{
            Picasso.with(context)
                    .load(movie.getBanner()).resize(150, 150)
                    //.centerCrop()
                    .into(holder.banner);
        }catch (Exception e){ e.printStackTrace(); }
        return convertView;
    }

    private static class ViewHolder {
        private TextView title,year;
        private ImageView banner;
        LinearLayout ll_rating;

        public ViewHolder(View v) {
            title = (TextView) v.findViewById(R.id.tv_row_title);
            year = (TextView) v.findViewById(R.id.tv_row_year);
            banner = (ImageView) v.findViewById(R.id.iv_row_banner);
            ll_rating = (LinearLayout) v.findViewById(R.id.ll_rating_card);
            ll_rating.setVisibility(View.INVISIBLE);
        }
    }
}
