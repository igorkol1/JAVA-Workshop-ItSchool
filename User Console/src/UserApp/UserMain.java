package UserApp;

import Domain.User;

import static Commons.ConsoleUtils.getInt;
import static Commons.ConsoleUtils.printOptions;

public class UserMain {

    private User contextUser;

    private final String[] options = {"1. Browse homework", "2. Manage solutions", "3. Edit own data", "4. Exit"};

    public UserMain(User contextUser) {
        this.contextUser = contextUser;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean performOption(int option) {
        int operation;
        boolean status = true;
        switch (option) {
            case 1:
                UserHomework userHomework = new UserHomework(contextUser);
                userHomework.printUserHomework();
                break;
            case 2:
                UserSolutions userSolutions = new UserSolutions(contextUser);
                printOptions(userSolutions.getOptions());
                System.out.println("\nType option number: ");
                operation = getInt();
                userSolutions.performOptions(operation);
                break;
            case 3:
                UserUpdate userUpdate = new UserUpdate(contextUser);
                userUpdate.updateUser();
                break;
            case 4:
                status = false;
                break;
            default:
                System.out.println("\nUnrecognized option. Please try again\n");
                break;
        }
        return status;
    }

}
