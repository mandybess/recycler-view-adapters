package com.mandybess.recyclerviewadapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class BookViewHolder extends BaseViewHolder<Book> {

  private TextView bookTitle;

  public BookViewHolder(View itemView) {
    super(itemView);
    bookTitle = (TextView) itemView.findViewById(R.id.list_item_book_title);
  }

  @Override
  public void bindView(Context context, Book book) {
    bookTitle.setText(book.title);
  }
}
