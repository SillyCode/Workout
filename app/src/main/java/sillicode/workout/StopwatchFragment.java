package sillicode.workout;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class StopwatchFragment extends Fragment implements View.OnClickListener {
    private int seconds = 0;
    private boolean stopwatch_running;
    private boolean wasRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            stopwatch_running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("was_running");
            if(wasRunning) {
                stopwatch_running = true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(layout);
        Button startButton = (Button) layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = (Button) layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = (Button) layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onStop() {
        super.onStop();
        wasRunning = stopwatch_running;
        stopwatch_running = false;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(wasRunning) {
            stopwatch_running = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = stopwatch_running;
        stopwatch_running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(wasRunning) {
            stopwatch_running = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", stopwatch_running);
        savedInstanceState.putBoolean("was_running", wasRunning);
    }

    public void onClickStart(View view) {
        if(!stopwatch_running) {
            stopwatch_running = true;
        }
    }

    public void onClickStop(View view) {
        stopwatch_running = false;
    }

    public void onClickReset(View view) {
        stopwatch_running = false;
        seconds = 0;
    }

    public void runTimer(View view) {
        final TextView timeView = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;
                String time = String.format("%02d:%02d:%02d", hours, minutes, sec);
                timeView.setText(time);
                if (stopwatch_running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.start_button: {
                onClickStart(v);
                break;
            }
            case R.id.stop_button: {
                onClickStop(v);
                break;
            }
            case R.id.reset_button: {
                onClickReset(v);
                break;
            }
        }
    }
}
