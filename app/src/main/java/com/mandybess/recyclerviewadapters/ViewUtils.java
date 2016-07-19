package com.mandybess.recyclerviewadapters;

public class ViewUtils {

  public static <T> T checkNotNull(T value, String message) {
    if (value == null) {
      throw new NullPointerException(message);
    }
    return value;
  }
}
