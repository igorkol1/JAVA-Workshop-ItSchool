import DAO.ExerciseDao;
import Domain.Exercise;

public class Main {
    public static void main(String[] args) {
        Exercise exercise = new Exercise("Test Exercise DAO","Test Exercise DAO Desc.");

        ExerciseDao exerciseDao = new ExerciseDao();

        int id = exerciseDao.create(exercise).getId();

        System.out.println("New exercise id: "+id);

        Exercise createdExercise = exerciseDao.read(id);

        System.out.println("New excercise title: "+createdExercise.getTitle());
        System.out.println("New excercise description: "+createdExercise.getDescription());

        createdExercise.setTitle("Test Exercise DAO 2");
        createdExercise.setDescription("Test Exercise DAO Desc. 2");

        exerciseDao.update(createdExercise);

        createdExercise = exerciseDao.read(id);

        System.out.println("New excercise title after update: "+createdExercise.getTitle());
        System.out.println("New excercise description after update: "+createdExercise.getDescription());

        exerciseDao.delete(id);
    }
}
