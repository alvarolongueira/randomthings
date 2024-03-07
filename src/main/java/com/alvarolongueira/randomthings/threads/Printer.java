package com.alvarolongueira.randomthings.threads;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Printer {

  public synchronized static void print(String text) {
    log.info(text);
  }
}
