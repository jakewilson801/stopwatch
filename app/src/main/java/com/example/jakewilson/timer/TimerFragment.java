package com.example.jakewilson.timer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jakewilson on 5/31/17.
 */

public class TimerFragment extends Fragment {
  TextView timer;
  public static TimerFragment newInstance() {
    return new TimerFragment();
  }

  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
    return inflater.inflate(R.layout.fragment_timer, parent, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    // Setup any handles to view objects here
    timer = (TextView) view.findViewById(R.id.timer_display);
  }

  public void upDateTime(int time) {
    timer.setText(String.valueOf(time));
  }
}
