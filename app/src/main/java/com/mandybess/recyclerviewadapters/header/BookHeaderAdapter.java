package com.mandybess.recyclerviewadapters.header;

import android.content.Context;
import android.view.ViewGroup;
import com.mandybess.recyclerviewadapters.Book;
import com.mandybess.recyclerviewadapters.BookViewHolder;
import com.mandybess.recyclerviewadapters.R;

import static android.view.LayoutInflater.from;
import static com.mandybess.recyclerviewadapters.ViewUtils.checkNotNull;

public class BookHeaderAdapter extends HeaderAdapter<BookViewHolder, Book> {

  private final Context context;
  public final static int HEADER_COUNT = 1;

  public BookHeaderAdapter(Context context) {
    this.context = context;
  }

  @Override
  protected BookViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
    checkNotNull(getHeader(), "header view may not be null");
    return new BookViewHolder(getHeader());
  }

  @Override
  protected BookViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
    return new BookViewHolder(from(context).inflate(R.layout.list_item_book, parent, false));
  }

  @Override
  protected void onBindHeaderViewHolder(BookViewHolder holder, int position) {
  }

  @Override
  protected void onBindItemViewHolder(BookViewHolder holder, int position) {
    holder.bindView(context, getItem(position));
  }

}
