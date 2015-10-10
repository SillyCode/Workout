package sillicode.workout;

/**
 * Created by mark on 10/7/15.
 */
public class Workout {
    private String name;
    private String description;

    public static final Workout[] workouts = {
        new Workout("Easy", "5 push ups\n1-legged squat"),
        new Workout("Medium", "200 push ups, and blah blah"),
        new Workout("Hard", "500 push ups"),
        new Workout("Insane", "Do until you die")
    };

    private Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

}
