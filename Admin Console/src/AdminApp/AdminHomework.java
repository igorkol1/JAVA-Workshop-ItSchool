package AdminApp;

import DAO.ExerciseDao;
import DAO.HomeworkDao;
import DAO.UserGroupDao;
import Domain.Exercise;
import Domain.Homework;
import Domain.UserGroup;

import java.time.LocalDateTime;
import java.util.List;

import static Commons.ConsoleUtils.getInt;

public class AdminHomework {

    private final String[] options = {"1. Print all homework", "2. Add homework", "3. Edit homework", "4. Deactivate homework", "5. Browser solutions", "6. Exit homework"};
    private HomeworkDao homeworkDao = new HomeworkDao();
    private UserGroupDao userGroupDao = new UserGroupDao();
    private ExerciseDao exerciseDao = new ExerciseDao();

    public String[] getOptions() {
        return options;
    }

    public void performOptions(int option) {
        switch (option) {
            case 1:
                printAllHomework();
                break;
            case 2:
                addNewHomework();
                break;
            case 3:
                updateHomework();
                break;
            case 4:
                deactivateHomework();
                break;
            case 5:
                browseSolutions();
                break;
            case 6:
                break;
            default:
                System.out.println("\nUnrecognized option. Please try again\n");
                break;
        }
    }

    private void printAllHomework() {
        List<Homework> homeworkList = homeworkDao.findAll();
        homeworkList.forEach(homework -> System.out.println(homework.toString()));
    }

    private void addNewHomework() {
        System.out.println("List of all groups:");
        userGroupDao.findAll()
                .forEach(userGroup -> System.out.println(userGroup.toString()));
        System.out.println("\nWhich group:");
        int groupId = getInt();
        UserGroup group = userGroupDao.read(groupId);
        if (group != null) {
            System.out.println("List of all exercises");
            exerciseDao.findAll()
                    .forEach(exercise -> System.out.println(exercise.toString()));
            System.out.println("\nWhich exercise:");
            int exerciseId = getInt();
            Exercise exercise = exerciseDao.read(exerciseId);
            if (exercise != null) {
                Homework homework = new Homework();
                homework.setGroupId(group.getId());
                homework.setExerciseId(exercise.getId());
                homework.setActive(true);
                homework.setCreated(LocalDateTime.now());
                Homework newHomework = homeworkDao.create(homework);
                if (newHomework != null) {
                    System.out.println("New homework id: " + newHomework.getId() + '\n');
                } else {
                    System.out.println("Fail to save new group\n");
                }
            } else {
                System.out.println("There is no exercise with id: " + exerciseId);
            }
        } else {
            System.out.println("There is no group with id: " + groupId);
        }
    }

    private void updateHomework() {
        System.out.println("\nProvide homework id:");
        int homeworkId = getInt();
        Homework homework = homeworkDao.read(homeworkId);
        if (homework != null) {
            System.out.println("Old group: " + homework.getGroupId() + " New group: ");
            int groupId = getInt();
            UserGroup group = userGroupDao.read(groupId);
            if (group != null) {
                System.out.println("\nOld exercise: " + homework.getExerciseId() + " New exercise:");
                int exerciseId = getInt();
                Exercise exercise = exerciseDao.read(exerciseId);
                if (exercise != null) {
                    homework.setGroupId(group.getId());
                    homework.setExerciseId(exercise.getId());
                    homework.setActive(true);
                    homeworkDao.update(homework);
                } else {
                    System.out.println("There is no exercise with id: " + exerciseId);
                }
            } else {
                System.out.println("There is no group with id: " + groupId);
            }
        } else {
            System.out.println("There is no homework with id: " + homeworkId);
        }
    }

    private void deactivateHomework() {
        System.out.println("\nProvide homework id:");
        int homeworkId = getInt();
        Homework homework = homeworkDao.read(homeworkId);
        if (homework != null) {
            homework.setActive(false);
            homeworkDao.update(homework);
            System.out.println("Homework with id: " + homeworkId + " is now inactive");
        } else {
            System.out.println("There is no homework with id: " + homeworkId);
        }
    }

    private void browseSolutions() {
        //todo
    }

}
