package Commons;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleUtils {

    public static int getInt() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("\nInput should be a number. Try one more time:");
        }
        return scan.nextInt();
    }

    public static void printOptions(String[] options) {
        Arrays.stream(options).forEach(System.out::println);
    }


}
