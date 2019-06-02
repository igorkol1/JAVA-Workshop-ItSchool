import Commons.Authorization;
import DAO.UserDao;
import Domain.User;

import static Commons.ConsoleUtils.getString;

public class Main {
    public static void main(String[] args) {
        User contextUser = login();
        System.out.println("User authorize");


    }

    private static User login() {
        Authorization authorization = new Authorization();
        User contextUser;
        while (true) {
            System.out.println("Enter email: ");
            String email = getString();
            System.out.println("Enter password: ");
            String password = getString();
            contextUser = authorization.authorize(email, password);
            if (contextUser != null) {
                return contextUser;
            } else {
                System.out.println("Incorrect user or password ");
            }
        }
    }
}
