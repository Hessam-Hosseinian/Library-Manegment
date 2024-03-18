
import java.text.ParseException;
import java.util.Scanner;
//?----------------------------------------------------------------------------------------------------------------------

public class Main {
    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);
        String input = new String();
        CommandOperation commandOperation = new CommandOperation();
        while (!input.equals("finish")) {
            input = scanner.nextLine();

            // if (input.equals("test")) { // for test and debugging

            // commandOperation.test();

            // }

            commandOperation.input(input);
        }

        scanner.close();
    }
}
// ?----------------------------------------------------------------------------------------------------------------------
