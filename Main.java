
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = "";
        CommandOperation commandOperation = new CommandOperation();
        while (!input.equals("finish")) {
            input = scanner.nextLine();
            commandOperation.input(input);
        }

    }
}