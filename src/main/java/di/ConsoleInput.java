package di;

import java.util.Scanner;

public class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    public String askStr(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
