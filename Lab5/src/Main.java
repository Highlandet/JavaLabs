public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("There are no CMD's arguments.");
        } else {
            System.out.println("CMD's arguments:");
            for (int i = 0; i < args.length; i++) {
                System.out.println("Argument " + (i + 1) + ": " + args[i]);
            }
        }
    }
}