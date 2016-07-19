package com.mandybess.recyclerviewadapters.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.mandybess.recyclerviewadapters.Book;
import com.mandybess.recyclerviewadapters.BookViewHolder;
import com.mandybess.recyclerviewadapters.R;
import java.util.ArrayList;
import java.util.List;

public class BookSection extends Section {

  private Context context;
  private List<Book> books;

  public BookSection(Context context, List<Book> books) {
    super(R.layout.list_item_header, R.layout.list_item_footer, R.layout.list_item_book);
    this.context = context;
    this.books = (books == null ? new ArrayList<Book>() : books);
  }

  @Override
  public int getContentItemsTotal() {
    return books.size();
  }

  @Override
  public ViewHolder onCreateHeaderViewHolder(View view) {
    return new BookViewHolder(view);
  }

  @Override
  public void onBindHeaderViewHolder(ViewHolder holder) {

  }

  @Override
  public ViewHolder onCreateFooterViewHolder(View view) {
    return new BookViewHolder(view);
  }

  @Override
  public void onBindFooterViewHolder(ViewHolder holder) {

  }

  @Override
  public ViewHolder onCreateItemViewHolder(View view) {
    return new BookViewHolder(view);
  }

  @Override
  public void onBindItemViewHolder(ViewHolder holder, int position) {
    if (position < books.size()) {
      ((BookViewHolder) holder).bindView(context, books.get(position));
    }
  }
}
