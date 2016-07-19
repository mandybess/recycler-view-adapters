package com.mandybess.recyclerviewadapters.multitype;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public abstract class AdapterItem<VH extends RecyclerView.ViewHolder> {

  public abstract VH onCreateViewHolder(ViewGroup parent);

  public abstract void onBindViewHolder(VH holder, int position);

  public abstract int getItemCount();

  public abstract int getItemViewType();
}

