package com.mandybess.recyclerviewadapters.header;

import com.mandybess.recyclerviewadapters.Book;

public class BookHeaderAdapterTest extends BaseHeaderAdapterTest<Book, BookHeaderAdapter> {

  @Override
  public BookHeaderAdapter makeAdapter() {
    return new BookHeaderAdapter(context);
  }

  @Override
  public Book makePojo() {
    return Book.makeDummyBook();
  }
}
