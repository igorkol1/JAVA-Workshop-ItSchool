package AdminApp;

import DAO.UserDao;
import Domain.User;

import java.util.List;

public class AdminUser {

    private final String[] options = {"1. Print all users", "2. Add new user", "3. Edit existing user", "4. Delete user", "5. Exit user management"};
    private UserDao userDao = new UserDao();

    public String[] getOptions() {
        return options;
    }

    public void performOptions(int option) {
        switch (option) {
            case 1:
                printAllUsers();
                break;
            case 5:
                break;
            default:
                System.out.println("\nUnrecognized option. Please try again\n");
                break;
        }
    }

    private void printAllUsers() {
        List<User> users = userDao.findAll();
        users.forEach(user -> System.out.println(user.toString()));
    }


}
