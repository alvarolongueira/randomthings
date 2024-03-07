package com.alvarolongueira.randomthings.threads;

import java.util.List;
import java.util.Set;

class CollectionHandler {

  public static void remove(List<Item> list, Item item) {
    synchronized (list) {
      list.remove(item);
    }
  }

  public static void add(Set<String> set, String key) {
    synchronized (set) {
      set.add(key);
    }
  }

  public static void remove(Set<String> set, String key) {
    synchronized (set) {
      set.remove(key);
    }
  }
}
