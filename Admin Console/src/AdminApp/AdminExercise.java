package AdminApp;

import DAO.ExerciseDao;
import Domain.Exercise;

import java.util.List;

public class AdminExercise {

    private final String[] options = {"1. Print all exercises", "2. Add new exercise", "3. Edit existing exercise", "4. Delete exercise", "5. Exit exercise management"};
    ExerciseDao exerciseDao = new ExerciseDao();

    public String[] getOptions() {
        return options;
    }

    public void performOptions(int option) {
        switch (option) {
            case 1:
                printAllExercise();
                break;
            case 2:
                addNewExercise();
                break;
            case 3:
                updateExercise();
                break;
            case 4:
                deleteExercise();
                break;
            case 5:
                break;
            default:
                System.out.println("\nUnrecognized option. Please try again\n");
                break;
        }
    }

    private void deleteExercise() {
    }

    private void updateExercise() {
    }

    private void addNewExercise() {
    }

    private void printAllExercise() {
        List<Exercise> exercises = exerciseDao.findAll();
        exercises.forEach(exercise -> System.out.println(exercise.toString()));
    }
}
