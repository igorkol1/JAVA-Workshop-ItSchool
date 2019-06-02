package UserApp;

import DAO.ExerciseDao;
import DAO.HomeworkDao;
import Domain.Homework;
import Domain.User;

import java.util.List;

public class UserHomework {

    private User contextUser;
    private HomeworkDao homeworkDao;
    private ExerciseDao exerciseDao;

    public UserHomework(User contextUser) {
        this.contextUser = contextUser;
        homeworkDao = new HomeworkDao();
        exerciseDao = new ExerciseDao();
    }

    public void printUserHomework() {
        List<Homework> homeworkList = homeworkDao.findAllForUser(contextUser);
        homeworkList.forEach(this::printHomeworkAndExercise);
    }

    protected void printHomeworkAndExercise(Homework homework) {
        System.out.println("Homework: " + homework.toString());
        System.out.println("Exercise: " + exerciseDao.read(homework.getExerciseId()).toString() + "\n");
    }
}
