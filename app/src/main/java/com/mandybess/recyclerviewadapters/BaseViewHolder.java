package com.mandybess.recyclerviewadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * This class should be extended by all {@link android.support.v7.widget.RecyclerView.ViewHolder}
 * to enforce standardized use of {@link #bindView(Context, Object)}
 * <p>
 * The following code snippet shows how to properly subclass {@link BaseViewHolder} using a simple
 * Country POJO:
 * <pre>
 * {@code
 * public class CountriesViewHolder extends BaseViewHolder<Country> {
 *
 *  private TextView name;
 *
 *  public CountriesViewHolder(View itemView) {
 *       super(itemView);
 *       name = (TextView) itemView.findViewById(R.id.list_item_country_name);
 *   }
 *
 *   @Override
 *   public void bindView(Context context, Country object) {
 *       name.setText(country.getName());
 *   }
 * }
 * </pre>
 *
 * @param <T> the POJO to be attached in {@link #bindView(Context, Object)}
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

  public BaseViewHolder(View itemView) {
    super(itemView);
  }

  public abstract void bindView(Context context, T object);
}

