import DAO.ExerciseDao;
import DAO.UserGroupDao;
import Domain.Exercise;
import Domain.UserGroup;

public class Main {
    public static void main(String[] args) {
//        Exercise exercise = new Exercise("Test Exercise DAO","Test Exercise DAO Desc.");
//
//        ExerciseDao exerciseDao = new ExerciseDao();
//
//        int id = exerciseDao.create(exercise).getId();
//
//        System.out.println("New exercise id: "+id);
//
//        Exercise createdExercise = exerciseDao.read(id);
//
//        System.out.println("New excercise title: "+createdExercise.getTitle());
//        System.out.println("New excercise description: "+createdExercise.getDescription());
//
//        createdExercise.setTitle("Test Exercise DAO 2");
//        createdExercise.setDescription("Test Exercise DAO Desc. 2");
//
//        exerciseDao.update(createdExercise);
//
//        createdExercise = exerciseDao.read(id);
//
//        System.out.println("New excercise title after update: "+createdExercise.getTitle());
//        System.out.println("New excercise description after update: "+createdExercise.getDescription());
//
//        exerciseDao.delete(id);

        UserGroup userGroup = new UserGroup("Test User Group DAO");

        UserGroupDao userGroupDao = new UserGroupDao();

        userGroupDao.create(userGroup);
        userGroup.setName("Test User Group DAO 2");
        userGroupDao.update(userGroup);
        userGroup = userGroupDao.read(userGroup.getId());
        userGroupDao.delete(userGroup.getId());


    }
}
