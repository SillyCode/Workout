package sillicode.workout;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {
    private long workoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(savedInstanceState != null) {
            workoutId = savedInstanceState.getLong("workoutid");
        }
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putLong("workoutid", workoutId);
    }

    public void setWorkout(long id) {
        this.workoutId = id;
    }

    public void onStart() {
        super.onStart();
        View view = getView(); //get fragment root view
        if(view != null) {
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            TextView description = (TextView) view.findViewById(R.id.textDescription);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            description.setText(workout.getDescription());
        }
    }
}
