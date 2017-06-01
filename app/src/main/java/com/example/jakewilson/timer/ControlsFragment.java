package com.example.jakewilson.timer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jakewilson on 5/31/17.
 */

public class ControlsFragment extends Fragment {
  Button start;
  EditText input;
  Button clear;

  public static ControlsFragment newInstance() {
    return new ControlsFragment();
  }

  public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
    return inflater.inflate(R.layout.fragment_controls, parent, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    final Timer timer = ((MainActivity) getActivity()).timer;
    input = (EditText) view.findViewById(R.id.time_input);
    start = (Button) view.findViewById(R.id.start_button);
    clear = (Button) view.findViewById(R.id.clear_button);
    start.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (timer.subscription == null || !timer.isSubscribed()) {
          start.setText("Stop");
          if (timer.currentTime == 0) {
            int time = Integer.parseInt(input.getText().toString());
            timer.startTimer(time);
          } else {
            timer.startTimer();
          }
        } else {
          start.setText("Start");
          timer.stop();
        }
      }
    });

    clear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        timer.clear();
      }
    });
  }

  public void setStartButtonLabel(){
    start.setText("Start");
  }
}
