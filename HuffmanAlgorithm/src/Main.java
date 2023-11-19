import java.io.BufferedReader;
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean hasToRun = true;
        while(hasToRun){
            System.out.println("Please, enter the program's operating mode\n" +
                    "If you want to encode your file, write \"Encoding\"\n" +
                    "If you want to decode your file, write \"Decoding\"\n" +
                    "If you want to find out the information of coding process, write " +
                    "\"Informing\"\n" +
                    "If you want to exit the program, write \"Exit\"");
            String operating_mode = scanner.nextLine();
            if("Encoding".equals(operating_mode)) {
                Encode.encode();
            } else if ("Decoding".equals(operating_mode)) {
                Decode.decode();
            } else if ("Informing".equals(operating_mode)) {
                Inform.inform();
            } else if ("Exit".equals(operating_mode)) {
                hasToRun = false;
                System.out.println("Goodbye!");
            } else {
                System.out.println("Unknown mode.");
            }
        }
    }
}