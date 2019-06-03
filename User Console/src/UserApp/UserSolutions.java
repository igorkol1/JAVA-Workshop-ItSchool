package UserApp;

import DAO.ExerciseDao;
import DAO.HomeworkDao;
import DAO.SolutionDao;
import Domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static Commons.ConsoleUtils.*;

public class UserSolutions {

    private User contextUser;
    private SolutionDao solutionDao;
    private HomeworkDao homeworkDao;
    private ExerciseDao exerciseDao;

    private final String[] options = {"1. Browse solutions", "2. Add solutions", "3. Edit solution", "4. Exit"};

    public UserSolutions(User contextUser) {
        this.contextUser = contextUser;
        solutionDao = new SolutionDao();
        homeworkDao = new HomeworkDao();
        exerciseDao = new ExerciseDao();
    }

    public String[] getOptions() {
        return options;
    }

    public void performOptions(int option) {
        switch (option) {
            case 1:
                printAllSolutions();
                break;
            case 2:
                addSolution();
                break;
            case 3:
                updateSolution();
                break;
            case 4:
                break;
            default:
                System.out.println("\nUnrecognized option. Please try again\n");
                break;
        }
    }

    private void printAllSolutions() {
        List<Solution> solutionList = solutionDao.findAllForUser(contextUser);
        solutionList.forEach(this::printSolutionWithDetails);
    }

    private void addSolution() {
        System.out.println("List of all unsolve homework:");
        printUnsolveHomework();
        System.out.println("Provide homework id: ");
        int homeworkId = getInt();
        Homework homework = homeworkDao.read(homeworkId);
        if(homework!=null){
            Solution solution = solutionDao.findSolutionForHomeworkAndUser(contextUser,homework);
            if(solution==null){
                solution = new Solution();
                solution.setCreated(LocalDateTime.now());
                solution.setUpdated(LocalDateTime.now());
                solution.setHomework_id(homework.getId());
                solution.setUser_id(contextUser.getId());
                System.out.println("Solution (link to onedrive): ");
                String solutionDescription = getString();
                solution.setDescription(solutionDescription);
                solution = solutionDao.create(solution);
                if(solution!=null){
                    System.out.println("Solution save with id: "+solution.getId());
                }else {
                    System.out.println("Fail to save solution");
                }
            }else {
                System.out.println("Solution for this homework already exist and has id: "+solution.getId());
            }
        }else {
            System.out.println("There is no homework with id: " + homeworkId);
        }
    }

    private void printUnsolveHomework() {
        List<Homework> unsolvedHomeworkList = homeworkDao.findAllUnsolvedForUser(contextUser);
        unsolvedHomeworkList.forEach(this::printHomeworkWithExercise);
    }

    private void updateSolution() {
        System.out.println("Provide solution id: ");
        int solutionId = getInt();
        Solution solution = solutionDao.read(solutionId);
        if(solution!=null){
            System.out.println("Old solution description: " + solution.getDescription() + " New description: ");
            String newDescription = getString();
            solution.setDescription(newDescription);
            solution.setUpdated(LocalDateTime.now());
            solutionDao.update(solution);
            System.out.println("Solution updated");
        }else {
            System.out.println("There is no solution with id: " + solutionId);
        }

    }

    private void printHomeworkWithExercise(Homework homework) {
        System.out.println("Homework: " + homework.toString());
        System.out.println("Exercise: " + exerciseDao.read(homework.getExerciseId()).toString() + "\n");
    }

    private void printSolutionWithDetails(Solution solution) {
        Homework homework = homeworkDao.read(solution.getHomework_id());
        System.out.println("Homework: " + homework.toString());
        System.out.println("Exercise: " + exerciseDao.read(homework.getExerciseId()).toString());
        System.out.println("Solution: " + solution.toString() + "\n");
    }

}
