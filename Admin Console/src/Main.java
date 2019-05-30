import AdminApp.AdminMain;

import static Commons.ConsoleUtils.getInt;
import static Commons.ConsoleUtils.printOptions;

public class Main {

    public static void main(String[] args) {
        boolean isRunning;
        AdminMain adminMain = new AdminMain();
        System.out.println("***** Admin Console ******\n");
        do {
            printOptions(adminMain.getOptions());
            System.out.println("\nType option number: ");
            int option = getInt();
            isRunning = adminMain.performOption(option);
        } while (isRunning);
        System.out.println("\n***** Closing Admin Console *****");
    }

}
