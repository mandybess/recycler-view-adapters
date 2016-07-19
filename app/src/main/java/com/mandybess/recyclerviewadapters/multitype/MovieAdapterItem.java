package com.mandybess.recyclerviewadapters.multitype;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.mandybess.recyclerviewadapters.Movie;
import com.mandybess.recyclerviewadapters.MovieViewHolder;
import com.mandybess.recyclerviewadapters.R;
import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;

public class MovieAdapterItem extends AdapterItem<MovieViewHolder> {

  private Context context;
  private List<Movie> movies;

  public MovieAdapterItem(Context context, List<Movie> movies) {
    this.context = context;
    this.movies = (movies == null ? new ArrayList<Movie>() : movies);
  }

  @Override
  public MovieViewHolder onCreateViewHolder(ViewGroup parent) {
    View view = from(context).inflate(R.layout.list_item_movie, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MovieViewHolder holder, int position) {
    if (position < movies.size()) {
      holder.bindView(context, movies.get(position));
    }
  }

  @Override
  public int getItemCount() {
    return movies.size();
  }

  @Override
  public int getItemViewType() {
    return MovieViewHolder.class.hashCode();
  }
}
