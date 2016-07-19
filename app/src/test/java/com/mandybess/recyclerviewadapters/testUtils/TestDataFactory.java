package com.mandybess.recyclerviewadapters.testUtils;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

  public static <T> List<T> makeList(T item, int count) {
    List<T> items = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      items.add(item);
    }
    return items;
  }
}
