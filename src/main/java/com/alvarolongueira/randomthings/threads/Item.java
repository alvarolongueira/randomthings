package com.alvarolongueira.randomthings.threads;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Item {

  private String key;
  private String name;
  private long time;

  public static Item of(String key, String name, long time) {
    return Item.builder().key(key).name(name).time(time).build();
  }
}
