import Commons.Authorization;
import DAO.UserDao;
import Domain.User;
import UserApp.UserMain;

import static Commons.ConsoleUtils.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("***** User Console ******\n");
        User contextUser = login();
        System.out.println("User authorized");
        boolean isRunning;
        UserMain userMain = new UserMain(contextUser);
        do {
            printOptions(userMain.getOptions());
            System.out.println("\nType option number: ");
            int option = getInt();
            isRunning = userMain.performOption(option);
        } while (isRunning);
        System.out.println("\n***** Closing User Console *****");
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
