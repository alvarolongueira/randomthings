package com.alvarolongueira.randomthings.threads;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class RunnableItem implements Runnable {

  private Item item;

  public static RunnableItem of(Item item) {
    return new RunnableItem(item);
  }

  @Override
  public void run() {
    try {
      long id = Thread.currentThread().getId();
      Printer.print(Instant.now() + " - Start " + this.getItem().getName() + " in thread " + id);
      Thread.sleep(this.getItem().getTime());
      Printer.print(Instant.now() + " - Finish " + this.getItem().getName() + " in thread " + id);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
