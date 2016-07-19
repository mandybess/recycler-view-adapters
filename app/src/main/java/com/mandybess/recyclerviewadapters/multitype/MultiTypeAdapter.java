package com.mandybess.recyclerviewadapters.multitype;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> {

  private List<AdapterItem> items;
  @Nullable Comparator<AdapterItem> comparator;

  public MultiTypeAdapter() {
    items = new ArrayList<>();
  }

  public MultiTypeAdapter(@Nullable Comparator<AdapterItem> comparator) {
    this();
    this.comparator = comparator;
  }

  @Override
  public int getItemViewType(int position) {
    int itemCount = 0;
    for (int i = 0; i < items.size(); i++) {
      itemCount += items.get(i).getItemCount();
      if (position < itemCount) {
        return items.get(i).getItemViewType();
      }
    }
    throw new IllegalArgumentException("Position is invalid");
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return getAdapterItem(viewType).onCreateViewHolder(parent);

  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    int adapterItemPosition = getAdapterItemPosition(position);
    getAdapterItem(getItemViewType(position)).onBindViewHolder(holder, adapterItemPosition);
  }

  @Override
  public int getItemCount() {
    int itemCount = 0;
    for (int i = 0, size = items.size(); i < size; i++) {
      AdapterItem item = items.get(i);
      itemCount += item.getItemCount();
    }
    return itemCount;
  }

  public List<AdapterItem> getAdapterItems() {
    return items;
  }

  public <T extends AdapterItem> T getAdapterItem(int viewType) {
    for (int i = 0; i < items.size(); i++) {
      T item = (T) items.get(i);
      if (item.getItemViewType() == viewType) {
        return item;
      }
    }
    throw new IllegalArgumentException("viewType is invalid");
  }

  /**
   * @param position index of item within all items from all AdapterItems
   * @return index of item within an AdapterItem
   */
  public int getAdapterItemPosition(int position) {
    int adapterItemCount;
    for (int i = 0; i < items.size(); i++) {
      adapterItemCount = items.get(i).getItemCount();
      if (position - adapterItemCount < 0) {
        break;
      }
      position -= adapterItemCount;
    }
    return position;
  }

  public <T extends AdapterItem> void addItems(List<T> items) {
    validateItems(items);
    this.items.addAll(items);
    if (comparator != null) {
      Collections.sort(this.items, comparator);
    }
    notifyDataSetChanged();
  }

  public <T extends AdapterItem> void addItem(T item) {
    if (item != null) {
      items.add(item);
      if (comparator != null) {
        Collections.sort(items, comparator);
      }
      notifyDataSetChanged();
    }
  }

  public <T extends AdapterItem> void removeItem(T item) {
    if (item != null) {
      this.items.remove(item);
      notifyDataSetChanged();
    }
  }

  public <T extends AdapterItem> void addItem(T item, int index) {
    if (item != null) {
      if (index > items.size()) {
        items.add(item);
      } else {
        items.add(index, item);
      }
      if (comparator != null) {
        Collections.sort(items, comparator);
      }
      notifyDataSetChanged();
    }
  }

  private <T extends AdapterItem> void validateItems(List<T> items) {
    if (items == null) {
      throw new IllegalArgumentException("You can't use a null List<T> instance.");
    }
  }
}

