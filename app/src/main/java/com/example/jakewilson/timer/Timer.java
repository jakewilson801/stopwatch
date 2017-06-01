package com.example.jakewilson.timer;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jakewilson on 5/31/17.
 */

public class Timer {
  interface TimerCallbacks {
    void onTimeChanged(int currentTime);

    void showToast(int secondsRemaining);

    void onCompleted();
  }

  public TimerCallbacks timerCallbacks;
  Subscription subscription;
  public int currentTime = 0;

  public void setTimerCallbacks(TimerCallbacks timerCallbacks) {
    this.timerCallbacks = timerCallbacks;
  }

  public void stop() {
    subscription.unsubscribe();
  }

  public void startTimer(int time) {
    currentTime = time;
    resetSubscription();
  }

  public void clear() {
    currentTime = 0;
    timerCallbacks.onTimeChanged(0);
    timerCallbacks.onCompleted();
    stop();
  }

  public boolean isSubscribed() {
    return !subscription.isUnsubscribed();
  }

  public void startTimer() {
    resetSubscription();
  }

  private void resetSubscription() {
    subscription = getTimerObs().subscribe(new Subscriber<Long>() {
      @Override
      public void onCompleted() { }

      @Override
      public void onError(Throwable e) { }

      @Override
      public void onNext(Long aLong) {
        handleHeartBeats();
      }
    });
  }

  private void handleHeartBeats() {
    currentTime--;
    if (currentTime % 5 == 0) {
      timerCallbacks.showToast(currentTime);
    }

    if (currentTime == 0) {
      timerCallbacks.onCompleted();
      subscription.unsubscribe();
    }

    timerCallbacks.onTimeChanged(currentTime);
  }

  private Observable<Long> getTimerObs() {
    return Observable.interval(1, TimeUnit.SECONDS)
        .startWith(1L)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .takeWhile(new Func1<Long, Boolean>() {
          @Override
          public Boolean call(Long aLong) {
            return currentTime > 0;
          }
        });
  }
}