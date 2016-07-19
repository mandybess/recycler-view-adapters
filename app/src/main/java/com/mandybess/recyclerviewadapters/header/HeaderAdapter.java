package com.mandybess.recyclerviewadapters.header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;

/**
 * @apiNote you can only set *one* Header view
 */
public abstract class HeaderAdapter<VH extends RecyclerView.ViewHolder, T>
    extends RecyclerView.Adapter<VH> {

  public static final int TYPE_HEADER = -2;
  public static final int TYPE_ITEM = -1;

  private View header;
  private List<T> items = Collections.EMPTY_LIST;

  /**
   * Invokes onCreateHeaderViewHolder or onCreateItemViewHolder methods
   * based on the view type param.
   */
  @Override
  public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    VH viewHolder;
    if (isHeaderType(viewType)) {
      viewHolder = onCreateHeaderViewHolder(parent, viewType);
    } else {
      viewHolder = onCreateItemViewHolder(parent, viewType);
    }
    return viewHolder;
  }

  /**
   * Invokes onBindHeaderViewHolder or onBindItemViewHolder methods based
   * on the position param.
   */
  @Override
  public void onBindViewHolder(VH holder, int position) {
    if (isHeaderPosition(position)) {
      onBindHeaderViewHolder(holder, position);
    } else {
      onBindItemViewHolder(holder, position);
    }
  }

  /**
   * @return if the position is equal to 0 and this adapter has a header, then this method will
   * return the constant  {@link #TYPE_HEADER} otherwise it will return {@link #TYPE_ITEM}
   * <p>
   * @note If your application has to support different types override this method and provide your
   * implementation. Remember that TYPE_HEADER and TYPE_ITEM are internal constants
   * can be used to identify an item given a position, try to use different values in your
   * application.
   */
  @Override
  public int getItemViewType(int position) {
    int viewType = TYPE_ITEM;
    if (isHeaderPosition(position)) {
      viewType = TYPE_HEADER;
    }
    return viewType;
  }

  /**
   * @return list item size + 1 if a header is present
   */
  @Override
  public int getItemCount() {
    int size = items.size();
    if (hasHeader()) {
      size++;
    }
    return size;
  }

  public View getHeader() {
    return header;
  }

  public T getItem(int position) {
    if (hasHeader() && hasItems()) {
      --position;
    }
    return items.get(position);
  }

  public void setHeader(View header) {
    this.header = header;
    notifyDataSetChanged();
  }

  public List<T> getItems() {
    return items;
  }

  public void setItems(List<T> items) {
    validateItems(items);
    this.items = items;
    notifyDataSetChanged();
  }

  protected abstract VH onCreateHeaderViewHolder(ViewGroup parent, int viewType);

  protected abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

  protected abstract void onBindHeaderViewHolder(VH holder, int position);

  protected abstract void onBindItemViewHolder(VH holder, int position);

  /**
   * Returns true if the position type parameter passed as argument is equals to 0 and the
   * adapter has a not null header already configured.
   */
  public boolean isHeaderPosition(int position) {
    return hasHeader() && position == 0;
  }

  /**
   * Returns true if the view type parameter passed as argument is equals to TYPE_HEADER.
   */
  protected boolean isHeaderType(int viewType) {
    return viewType == TYPE_HEADER;
  }

  /**
   * Returns true if the header configured is not null.
   */
  public boolean hasHeader() {
    return getHeader() != null;
  }

  private boolean hasItems() {
    return items.size() > 0;
  }

  private void validateItems(List<T> items) {
    if (items == null) {
      throw new IllegalArgumentException("You can't use a null List<Item> instance.");
    }
  }
}
