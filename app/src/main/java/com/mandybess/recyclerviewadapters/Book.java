package com.mandybess.recyclerviewadapters;

import java.util.ArrayList;
import java.util.List;

public class Book {

  protected String title;
  protected String author;

  public static Book makeDummyBook() {
    Book book = new Book();
    book.title = "Book Title";
    book.author = "Amanda";
    return book;
  }

  public static Book makeDummyBook(int count) {
    Book book = new Book();
    book.title = "Book: " + count;
    book.author = "Amanda";
    return book;
  }

  public static List<Book> makeBooks(int count) {
    List<Book> items = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      items.add(Book.makeDummyBook(i));
    }
    return items;
  }
}
