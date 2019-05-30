package AdminApp;

import DAO.UserDao;
import Domain.User;

import java.util.List;

import static Commons.ConsoleUtils.getInt;
import static Commons.ConsoleUtils.getString;

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
            case 2:
                addNewUser();
                break;
            case 4:
                deleteUser();
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

    private void addNewUser() {
        System.out.println("\nProvide new user data:");
        System.out.println("Name: ");
        String newUserName = getString();
        System.out.println("Email: ");
        String newUserEmail = getString();
        System.out.println("Password: ");
        String newUserPassword = getString();
        User newUser = new User(newUserName, newUserEmail, newUserPassword);
        newUser = userDao.create(newUser);
        if (newUser != null) {
            System.out.println("New user id: " + newUser.getId() + '\n');
        } else {
            System.out.println("Fail to save new user\n");
        }
    }

    private void deleteUser() {
        System.out.println("\nProvide user id:");
        int userId = getInt();
        userDao.delete(userId);
        System.out.println("User with id " + userId + " is deleted from database");
    }
}
