package com.alvarolongueira.randomthings.trialcountermultithread;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CounterMultithread {


  public static void main(String[] args) {
    // This exercise is not intended to be run
  }

  // QUESTION

  public static class RequestCounterQuestion {

    public static enum Application {
      APP_1, APP_2, APP_3, APP_4;
    }

    public RequestCounterQuestion(Collection<Application> validApplications) {
      // TODO: Implement this method
    }

    public void countRequest(Application sourceApplication) {
      // TODO: Implement this method
    }

    public Set<Application> getApplications() {
      // TODO: Implement this method
      return null;
    }

    public long getTotalRequests() {
      // TODO: Implement this method
      return 0L;
    }

    public long getRequestsByApplication(Application sourceApplication) {
      // TODO: Implement this method
      return 0L;
    }
  }

  // ANSWER

  public static class RequestCounter {

    private final static Map<Application, AtomicLong> map = new ConcurrentHashMap<>();

    public RequestCounter(Collection<Application> validApplications) {
      // With that name, I guess we don't need to check validApplications
      validApplications.stream().forEach(current -> map.put(current, new AtomicLong(0L)));
    }

    public void countRequest(Application sourceApplication) {
      map.get(sourceApplication).incrementAndGet();
    }

    public Set<Application> getApplications() {
      return new HashSet<>(Arrays.asList(Application.values()));
    }

    public long getTotalRequests() {
      long total = 0L;

      //Option 1, classic and easy way
      for (AtomicLong number : map.values()) {
        total += number.get();
      }

      //Option 2: I'm not sure this is the correct way, but it is similar
      map.values().stream().reduce(new AtomicLong(0L), (a, b) -> new AtomicLong(a.get() + b.get()));
      return total;
    }

    public long getRequestsByApplication(Application sourceApplication) {
      return map.getOrDefault(sourceApplication, new AtomicLong(0L)).get();
    }

    public static enum Application {
      APP_1, APP_2, APP_3, APP_4;
    }
  }


}
