enum Smth {
    Smth1,
    Smth2,
    Smth3
}
public class Main {
    public static void main(String[] args){
        // byte
        byte byte_value = 2;
        switch (byte_value) {
            case 1:
                System.out.println("Значение равно 1");
                break;
            case 2:
                System.out.println("Значение равно 2");
                break;
            default:
                System.out.println("Значение не равно 1 или 2");
        }

        // short
        short short_value = 100;
        switch (short_value) {
            case 100:
                System.out.println("Значение равно 100");
                break;
            case 200:
                System.out.println("Значение равно 500");
                break;
            default:
                System.out.println("Значение не равно 100 или 500");
        }

        // int
        int int_value = 3;
        switch (int_value) {
            case 1:
                System.out.println("Значение равно 1");
                break;
            case 3:
                System.out.println("Значение равно 3");
                break;
            default:
                System.out.println("Значение не равно 1 или 3");
        }

        // char
        char char_value = 'A';
        switch (char_value) {
            case 'A':
                System.out.println("Значение равно 'A'");
                break;
            case 'B':
                System.out.println("Значение равно 'B'");
                break;
            default:
                System.out.println("Значение не равно 'A' или 'B'");
        }

        // String
        String string_value = "Java";
        switch (string_value) {
            case "Java":
                System.out.println("Значение равно 'Java'");
                break;
            case "Love":
                System.out.println("Значение равно 'Love'");
                break;
            default:
                System.out.println("Значение не равно 'Hello' или 'World'");
        }

        //enum
        Smth my_smth = Smth.Smth1;
        switch (my_smth) {
            case Smth1:
                System.out.println("Значение равно Smth1");
                break;
            case Smth2:
                System.out.println("Значение равно Smth2");
                break;
            case Smth3:
                System.out.println("Значение равно Smth3");
                break;
            default:
                System.out.println("Значение не равно Smth1, Smth2 или Smth3");
        }
    }
}