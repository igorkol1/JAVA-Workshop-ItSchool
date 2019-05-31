package AdminApp;

import static Commons.ConsoleUtils.getInt;
import static Commons.ConsoleUtils.printOptions;

public class AdminMain {

    private final String[] options = {"1. User management", "2. Exercise management", "3. Groups management", "4. Exit"};

    public boolean performOption(int option) {
        int operation;
        boolean status = true;
        switch (option) {
            case 1:
                AdminUser adminUser = new AdminUser();
                printOptions(adminUser.getOptions());
                System.out.println("\nType option number: ");
                operation = getInt();
                adminUser.performOptions(operation);
                break;
            case 2:
                AdminExercise adminExercise = new AdminExercise();
                printOptions(adminExercise.getOptions());
                System.out.println("\nType option number: ");
                operation = getInt();
                adminExercise.performOptions(operation);
                break;
            case 3:
                AdminGroup adminGroup = new AdminGroup();
                printOptions(adminGroup.getOptions());
                System.out.println("\nType option number: ");
                operation = getInt();
                adminGroup.performOptions(operation);
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

    public String[] getOptions() {
        return options;
    }
}
