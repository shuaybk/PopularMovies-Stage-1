package com.example.android.popularmovies_stage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private ArrayList<Movie> movieList;

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.mContext = context;
        this.movieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final String movieTitle = movieList.get(position).getTitle();

        Picasso.get().load(movieList.get(position).getPoster()).into(holder.mIvPoster);
        System.out.println(movieList.get(position).getPoster());

        holder.mLiParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, movieTitle, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvPoster;
        LinearLayout mLiParentLayout;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mIvPoster = (ImageView) itemView.findViewById(R.id.iv_li_poster);
            mLiParentLayout = (LinearLayout) itemView.findViewById(R.id.li_parent_layout);
        }
    }
}
