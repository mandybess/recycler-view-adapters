package com.mandybess.recyclerviewadapters.section;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  public final static int VIEW_TYPE_HEADER = 0;
  public final static int VIEW_TYPE_FOOTER = 1;
  public final static int VIEW_TYPE_ITEM = 2;

  private LinkedHashMap<String, Section> sections;
  private HashMap<String, Integer> sectionViewTypeNumbers;

  private int viewTypeCount = 0;
  public final static int VIEW_TYPE_QTY = 3;

  public SectionAdapter() {
    sections = new LinkedHashMap<>();
    sectionViewTypeNumbers = new HashMap<>();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewHolder viewHolder = null;

    for (Map.Entry<String, Integer> entry : sectionViewTypeNumbers.entrySet()) {
      if (viewType >= entry.getValue() && viewType < entry.getValue() + VIEW_TYPE_QTY) {

        Section section = sections.get(entry.getKey());
        int sectionViewType = viewType - entry.getValue();

        switch (sectionViewType) {
          case VIEW_TYPE_HEADER: {
            Integer resId = section.getHeaderResourceId();

            if (resId == null) {
              throw new NullPointerException("Missing 'header' resource id");
            }

            View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
            // get the header viewholder from the section
            viewHolder = section.onCreateHeaderViewHolder(view);
            break;
          }
          case VIEW_TYPE_FOOTER: {
            Integer resId = section.getFooterResourceId();

            if (resId == null) {
              throw new NullPointerException("Missing 'footer' resource id");
            }

            View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
            // get the footer viewholder from the section
            viewHolder = section.onCreateFooterViewHolder(view);
            break;
          }
          case VIEW_TYPE_ITEM: {
            View view = LayoutInflater.from(parent.getContext())
                .inflate(section.getItemResourceId(), parent, false);
            // get the item viewholder from the section
            viewHolder = section.onCreateItemViewHolder(view);
            break;
          }
          default:
            throw new IllegalArgumentException("Invalid viewType");
        }
      }
    }

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    int currentPos = 0;

    for (Map.Entry<String, Section> entry : sections.entrySet()) {
      Section section = entry.getValue();

      int sectionTotal = section.getSectionItemsTotal();

      // check if position is in this section
      if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {

        if (section.hasHeader()) {
          if (position == currentPos) {
            // delegate the binding to the section header
            getSectionForPosition(position).onBindHeaderViewHolder(holder);
            return;
          }
        }

        if (section.hasFooter()) {
          if (position == (currentPos + sectionTotal - 1)) {
            // delegate the binding to the section header
            getSectionForPosition(position).onBindFooterViewHolder(holder);
            return;
          }
        }

        // delegate the binding to the section content
        getSectionForPosition(position)
            .onBindItemViewHolder(holder, getItemIndexForSection(position));
        return;
      }

      currentPos += sectionTotal;
    }

    throw new IndexOutOfBoundsException("Invalid position");
  }

  @Override
  public int getItemCount() {
    int count = 0;

    for (Map.Entry<String, Section> entry : sections.entrySet()) {
      Section section = entry.getValue();
      count += section.getSectionItemsTotal();
    }
    return count;
  }

  @Override
  public int getItemViewType(int position) {
    int currentPos = 0;

    for (Map.Entry<String, Section> entry : sections.entrySet()) {
      Section section = entry.getValue();

      int sectionTotal = section.getSectionItemsTotal();

      // check if position is in this section
      if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {

        int viewType = sectionViewTypeNumbers.get(entry.getKey());

        if (section.hasHeader()) {
          if (position == currentPos) {
            return viewType + VIEW_TYPE_HEADER;
          }
        }

        if (section.hasFooter()) {
          if (position == (currentPos + sectionTotal - 1)) {
            return viewType + VIEW_TYPE_FOOTER;
          }
        }
        return viewType + VIEW_TYPE_ITEM;
      }
      currentPos += sectionTotal;
    }

    throw new IndexOutOfBoundsException("Invalid position");
  }

  /*
   * Returns the ViewType of an item based on the position in the adapter:
   *
   * @param position position in the adapter
   * @return {@link SectionAdapter#VIEW_TYPE_HEADER}, {@link SectionAdapter#VIEW_TYPE_FOOTER},
   * {@link SectionAdapter#VIEW_TYPE_ITEM}
   */
  public int getSectionItemViewType(int position) {
    int viewType = getItemViewType(position);

    return viewType % VIEW_TYPE_QTY;
  }

  /**
   * Returns the Section object for a position in the adapter
   *
   * @param position position in the adapter
   * @return Section object for that position
   */
  public Section getSectionForPosition(int position) {

    int currentPos = 0;

    for (Map.Entry<String, Section> entry : sections.entrySet()) {
      Section section = entry.getValue();

      int sectionTotal = section.getSectionItemsTotal();

      // check if position is in this section
      if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {
        return section;
      }

      currentPos += sectionTotal;
    }

    throw new IndexOutOfBoundsException("Invalid position");
  }

  /**
   * Return the item position relative to the section.
   *
   * @param position position of the item in the adapter
   * @return position of the item in the section
   */
  public int getItemIndexForSection(int position) {
    int currentPos = 0;

    for (Map.Entry<String, Section> entry : sections.entrySet()) {
      Section section = entry.getValue();

      int sectionTotal = section.getSectionItemsTotal();

      // check if position is in this section
      if (position >= currentPos && position <= (currentPos + sectionTotal - 1)) {
        return position - currentPos - (section.hasHeader() ? 1 : 0);
      }

      currentPos += sectionTotal;
    }

    throw new IndexOutOfBoundsException("Invalid position");
  }

  /**
   * Add a section
   *
   * @param tag unique identifier of the section
   * @param section section to be added
   */
  public void addSection(String tag, Section section) {
    this.sections.put(tag, section);
    this.sectionViewTypeNumbers.put(tag, viewTypeCount);
    viewTypeCount += VIEW_TYPE_QTY;
    notifyDataSetChanged();
  }

  /**
   * Return the section with the tag provided
   *
   * @param tag unique identifier of the section
   * @return section
   */
  public Section getSection(String tag) {
    return this.sections.get(tag);
  }

  /**
   * Remove section
   *
   * @param tag unique identifier of the section
   */
  public void removeSection(String tag) {
    this.sections.remove(tag);
    notifyDataSetChanged();
  }

  /**
   * Remove all sections
   */
  public void removeAllSections() {
    this.sections.clear();
    notifyDataSetChanged();
  }
}

