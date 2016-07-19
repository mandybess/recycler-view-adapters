package com.mandybess.recyclerviewadapters.section;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class Section {

  Integer headerResourceId;
  Integer footerResourceId;
  int itemResourceId;

  public Section(@Nullable Integer headerResourceId, @Nullable Integer footerResourceId,
      @NonNull int itemResourceId) {
    this.headerResourceId = headerResourceId;
    this.footerResourceId = footerResourceId;
    this.itemResourceId = itemResourceId;
  }

  /**
   * Check if this Section has a header
   *
   * @return true if this Section has a header
   */
  public final boolean hasHeader() {
    return headerResourceId != null;
  }

  /**
   * Check if this Section has a footer
   *
   * @return true if this Section has a footer
   */
  public final boolean hasFooter() {
    return footerResourceId != null;
  }

  /**
   * Return the layout resource id of the header
   *
   * @return layout resource id of the header
   */
  public final Integer getHeaderResourceId() {
    return headerResourceId;
  }

  /**
   * Return the layout resource id of the footer
   *
   * @return layout resource id of the footer
   */
  public final Integer getFooterResourceId() {
    return footerResourceId;
  }

  /**
   * Return the layout resource id of the item
   *
   * @return layout resource id of the item
   */
  public final int getItemResourceId() {
    return itemResourceId;
  }

  /**
   * Return the total of items of this Section
   *
   * @return total of items of this Section
   */
  public abstract int getContentItemsTotal();

  /**
   * Return the total of items of this Section, including content items
   * plus header and footer
   *
   * @return total of items of this section
   */
  public final int getSectionItemsTotal() {
    return getContentItemsTotal() + (hasHeader() ? 1 : 0) + (hasFooter() ? 1 : 0);
  }

  /**
   * Return the ViewHolder for the Header of this Section
   *
   * @param view View inflated by resource returned by getHeaderResourceId
   * @return ViewHolder for the Header of this Section
   */
  public abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(View view);

  /**
   * Bind the data to the ViewHolder for the Header of this Section
   *
   * @param holder ViewHolder for the Header of this Section
   */
  public abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder);

  /**
   * Return the ViewHolder for the Footer of this Section
   *
   * @param view View inflated by resource returned by getFooterResourceId
   * @return ViewHolder for the Footer of this Section
   */
  public abstract RecyclerView.ViewHolder onCreateFooterViewHolder(View view);

  /**
   * Bind the data to the ViewHolder for the Footer of this Section
   *
   * @param holder ViewHolder for the Footer of this Section
   */
  public abstract void onBindFooterViewHolder(RecyclerView.ViewHolder holder);

  /**
   * Return the ViewHolder for a single Item of this Section
   *
   * @param view View inflated by resource returned by getItemResourceId
   * @return ViewHolder for the Item of this Section
   */
  public abstract RecyclerView.ViewHolder onCreateItemViewHolder(View view);

  /**
   * Bind the data to the ViewHolder for an Item of this Section
   *
   * @param holder ViewHolder for the Item of this Section
   * @param position position of the item in the Section, not in the RecyclerView
   */
  public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);

}

