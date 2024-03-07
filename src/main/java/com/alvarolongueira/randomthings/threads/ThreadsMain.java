package com.alvarolongueira.randomthings.threads;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ThreadsMain {

  public static void main(String[] args) {
    startProcess();
  }

  private static void startProcess() {
    List<Integer> a = List.of();
    List<Item> items = Collections.synchronizedList(getItems());
    Set<String> keys = Collections.synchronizedSet(new HashSet<>());

    Instant start = Instant.now();

    while (!items.isEmpty()) {
      Optional<RunnableItem> nextToRun = calculateNext(items, keys);
      nextToRun.ifPresent(runnableItem -> {
        CollectionHandler.remove(items, runnableItem.getItem());
        CompletableFuture.runAsync(
            nextToRun.get()).thenAccept(result ->
            CollectionHandler.remove(keys, runnableItem.getItem().getKey()));
      });

      if (Duration.between(start, Instant.now()).toSeconds() > 10) {
        Printer.print("Security break");
        break;
      }
    }
  }

  private static synchronized Optional<RunnableItem> calculateNext(List<Item> items, Set<String> keys) {
    if (keys.size() >= 2) {
      return Optional.empty();
    }

    for (Item item : items) {
      if (!keys.contains(item.getKey())) {
        CollectionHandler.add(keys, item.getKey());
        return Optional.of(RunnableItem.of(item));
      }
    }
    return Optional.empty();
  }

  private static List<Item> getItems() {
    return new ArrayList<>(List.of(
        Item.of("A", "A1", 3000),
        Item.of("A", "A2", 500),
        Item.of("B", "B1", 500),
        Item.of("D", "D1", 500),
        Item.of("C", "C1", 3000),
        Item.of("B", "B2", 1000),
        Item.of("A", "A3", 1000),
        Item.of("E", "E", 1000),
        Item.of("F", "F", 1000),
        Item.of("G", "G", 1000)
        // A1(3)                      -> A2(0,5) -> B2(1)       -> E(1)        -> G(1)
        // B1(0,5) -> D1(0,5) -> C1(3)                  -> A3(1)        -> F(1)
    ));
  }


}
