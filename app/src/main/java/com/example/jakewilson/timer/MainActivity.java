package com.example.jakewilson.timer;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
  Timer timer = new Timer();

  TimerFragment timerFragment = TimerFragment.newInstance();
  ControlsFragment controlsFragment = ControlsFragment.newInstance();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.add(R.id.timer_host, timerFragment);
    ft.add(R.id.controls_host, controlsFragment);
    ft.commit();

    timer.setTimerCallbacks(new Timer.TimerCallbacks() {
      @Override
      public void onTimeChanged(int currentTime) {
        timerFragment.upDateTime(currentTime);
      }

      @Override
      public void showToast(int currentTime) {
        Toast.makeText(MainActivity.this, String.valueOf(currentTime), Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onCompleted() {
        ImageView catImage = (ImageView) findViewById(R.id.cat_image);
        catImage.setVisibility(View.VISIBLE);
        controlsFragment.setStartButtonLabel();
      }
    });
  }
}
