package com.example.mark.popmovie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mark.popmovie.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mark on 1/11/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    MovieAdapter(Context context, ArrayList<Movie> movies)
    {
        this.context = context;
        if (movies == null)
            throw new IllegalArgumentException("Movie list cannot be null");
        this.movies = movies;
    }


                class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
                {
                    TextView textView;
                    ImageView imageView;
                    Movie movie;

                    public MovieViewHolder(View view)
                    {
                        super(view);
                        //imageView = (ImageView) view.findViewById(R.id.iv_movie_poster);
                        textView = (TextView) view.findViewById(R.id.tv_movie_poster);
                        view.setOnClickListener(this);
                    }

                    public void bind(Movie m)
                    {
                        Log.d("INFO", m.getImgPrefix()+m.getImagePath());
                        textView.setText(m.getTitle());
// Picasso.with(context).load(m.getImgPrefix()+m.getImagePath()).into(imageView);
                        //if (m.getImagePath() != null)
                        //    Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
                        movie = m;
                    }

                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), "clicked", Toast.LENGTH_SHORT).show();
                        Intent movieDetailIntent = new Intent(view.getContext(), MovieDetailActivity.class);
                        movieDetailIntent.putExtra("MOVIE", movie);
                        view.getContext().startActivity(movieDetailIntent);
                    }
                }



    @Override
    public int getItemCount() {
        return movies.size();
    }

    /**
     * Inflate the view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflatedView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.tv_movie, parent, false);

        MovieViewHolder movieViewHolder = new MovieViewHolder(inflatedView);
        return movieViewHolder;
    }

    /**
     * Bind the data associated with the model
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }
}
