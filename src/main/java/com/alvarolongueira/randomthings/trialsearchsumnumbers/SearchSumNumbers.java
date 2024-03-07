package com.alvarolongueira.randomthings.trialsearchsumnumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchSumNumbers {

  public static void main(String args[]) throws Exception {
    start(new Integer[]{1, 3, 6, 9, 11, 14, 16, 19, 23, 27}, 20);
  }

  public static void start(Integer[] array, int number) {

    //remove bigger numbers from list
    List<Integer> list = Arrays.asList(array);
    List<Integer> filteredList = new ArrayList<>();
    for (Integer current : list) {
      if (current <= number) {
        filteredList.add(current);
      }
    }

    //Option 1
    boolean founded = false;

    for (int i = 0; i < filteredList.size(); i++) {
      for (int j = i; j < filteredList.size(); j++) {

        int possibleSolution = filteredList.get(i) + filteredList.get(j);
        if (possibleSolution > number) {
          break;
        }
        if (possibleSolution == number) {
          System.out.println("Numbers: " + filteredList.get(i) + " + " + filteredList.get(j));
          founded = true;
          break;
        }
      }
      if (founded) {
        break;
      }
    }

    founded = false;

    //Option 2
    Set<Integer> aux = new HashSet<>(filteredList);
    for (int i = 0; i < filteredList.size(); i++) {
      int possible = number - filteredList.get(i);
      if (aux.contains(possible)) {
        System.out.println("Numbers: " + filteredList.get(i) + " + " + possible);
        if (founded) {
          break;
        }
      }
    }

  }
}
