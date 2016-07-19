package com.mandybess.recyclerviewadapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class MovieViewHolder extends BaseViewHolder<Movie> {

  private TextView title;
  private TextView duration;

  public MovieViewHolder(View itemView) {
    super(itemView);
    title = (TextView) itemView.findViewById(R.id.list_item_movie_title);
    duration = (TextView) itemView.findViewById(R.id.list_item_movie_duration);
  }

  @Override
  public void bindView(Context context, Movie movie) {
    title.setText(movie.title);
    duration.setText(getFormattedDuration(movie.duration));
  }

  private String getFormattedDuration(int duration) {
    return String.format("%s minutes", duration);
  }
}
