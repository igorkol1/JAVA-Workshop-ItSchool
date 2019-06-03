package UserApp;

import DAO.UserDao;
import Domain.User;

import static Commons.ConsoleUtils.getString;

public class UserUpdate {

    private User contextUser;
    private UserDao userDao;

    public UserUpdate(User contextUser) {
        this.contextUser = contextUser;
        userDao = new UserDao();
    }

    public void updateUser() {
        System.out.println("Old username: " + contextUser.getUserName() + " New username: ");
        String newUsername = getString();
        System.out.println("Old email: " + contextUser.getEmail() + " New email: ");
        String newEmail = getString();
        System.out.println("New password: ");
        String newPassword = getString();
        contextUser.setUserName(newUsername);
        contextUser.setEmail(newEmail);
        contextUser.setPassword(newPassword);
        userDao.update(contextUser);
        System.out.println("User successfully updated");
    }
}
