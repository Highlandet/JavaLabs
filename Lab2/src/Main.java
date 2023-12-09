import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the arbitrary number of word, separated by spaces:");
        while (scanner.hasNext()) {
            String word = scanner.next();
            System.out.println("Your word is " + word);
        }
        scanner.close();
    }
}