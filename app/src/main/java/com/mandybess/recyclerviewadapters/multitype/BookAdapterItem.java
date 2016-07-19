package com.mandybess.recyclerviewadapters.multitype;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import com.mandybess.recyclerviewadapters.Book;
import com.mandybess.recyclerviewadapters.BookViewHolder;
import com.mandybess.recyclerviewadapters.R;
import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;

public class BookAdapterItem extends AdapterItem<BookViewHolder> {

  private Context context;
  private List<Book> books;

  public BookAdapterItem(Context context, List<Book> books) {
    this.context = context;
    this.books = (books == null ? new ArrayList<Book>() : books);
  }

  @Override
  public BookViewHolder onCreateViewHolder(ViewGroup parent) {
    View view = from(context).inflate(R.layout.list_item_book, parent, false);
    return new BookViewHolder(view);
  }

  @Override
  public void onBindViewHolder(BookViewHolder holder, int position) {
    if (position < books.size()) {
      holder.bindView(context, books.get(position));
    }
  }

  @Override
  public int getItemCount() {
    return books.size();
  }

  /**
   * The importance of this method is to return a unique ID separate from all the other {@link
   * AdapterItem}s being used in a particular {@link MultiTypeAdapter}. In *most* cases each {@link
   * AdapterItem} will have its own {@link ViewHolder} so it is safe to use the {@link ViewHolder}
   * hash as a unique identifier
   */
  @Override
  public int getItemViewType() {
    return BookViewHolder.class.hashCode();
  }
}
