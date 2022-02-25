package com.game.cricketgame.hepler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UtilClass {
  private UtilClass(){}
  public static <T> List<T> iterableToCollection(Iterable<T> iterable) {
    List<T> collection = new ArrayList<T>();
    for (T e : iterable) {
      collection.add(e);
    }
    return collection;
  }

  public static final Random random = new Random();
}
