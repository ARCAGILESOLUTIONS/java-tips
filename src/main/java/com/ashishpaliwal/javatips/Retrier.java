package com.ashishpaliwal.javatips;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Tip on how to use Guava Retryer
 */
public class Retrier {

  static class UrlFetcher implements Callable<Boolean> {

    private String url;
    // To emulate failure
    private String opMode;

    public UrlFetcher(String url, String opMode) {
      this.url = url;
      this.opMode = opMode;
    }

    @Override
    public Boolean call() throws Exception {

      System.out.println("Trying to get data from "+url + "at time " + new Date());
      // fetch URL content
      // store somewhere
      if("fail".equals(opMode)) {
        throw new TimeoutException("Connection timed out");
      }
      System.out.println("URL Content fetched");
      return true;
    }
  }

  public static void main(String[] args) {

    Retryer<Boolean> retrier = RetryerBuilder.<Boolean>newBuilder()
            .retryIfExceptionOfType(TimeoutException.class)
            .retryIfRuntimeException()
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();

    try {
      retrier.call(new UrlFetcher("http://www.google.com", "normal"));
      retrier.call(new UrlFetcher("http://www.doesnotexist.com", "fail"));
    } catch (RetryException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

}
