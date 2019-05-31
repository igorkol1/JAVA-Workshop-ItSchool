package AdminApp;

import DAO.ExerciseDao;
import Domain.Exercise;

import java.util.List;

import static Commons.ConsoleUtils.getInt;
import static Commons.ConsoleUtils.getString;

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
        System.out.println("\nProvide exercise id:");
        int exerciseId = getInt();
        exerciseDao.delete(exerciseId);
        System.out.println("Exercise with id " + exerciseId + " is deleted from database");
    }

    private void updateExercise() {
        System.out.println("\nProvide exercise id:");
        int exerciseId = getInt();
        Exercise exercise = exerciseDao.read(exerciseId);
        if (exercise != null) {
            System.out.println("\nProvide new exercise data:");
            System.out.println("Old title: " + exercise.getTitle() + " New title: ");
            exercise.setTitle(getString());
            System.out.println("Old description: " + exercise.getDescription() + " New description: ");
            exercise.setDescription(getString());
            exerciseDao.update(exercise);
            System.out.println("Exercise with id " + exercise.getId() + " is updated");
        } else {
            System.out.println("There is not exercise with id: " + exerciseId);
        }
    }

    private void addNewExercise() {
        System.out.println("\nProvide new exercise data:");
        System.out.println("Title: ");
        String newExerciseTitle = getString();
        System.out.println("Description: ");
        String newExerciseDescription = getString();
        Exercise newExercise = new Exercise(newExerciseTitle, newExerciseDescription);
        newExercise = exerciseDao.create(newExercise);
        if (newExercise != null) {
            System.out.println("New user id: " + newExercise.getId() + '\n');
        } else {
            System.out.println("Fail to save new user\n");
        }
    }

    private void printAllExercise() {
        List<Exercise> exercises = exerciseDao.findAll();
        exercises.forEach(exercise -> System.out.println(exercise.toString()));
    }
}
