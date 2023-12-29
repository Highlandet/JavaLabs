import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Arguments format: <command> <file path>, where command is Encoding [compress](default), Decoding [decompress], Informing [inform]");
        String[] nargs = new String[args.length];
        System.arraycopy(args, 0, nargs, 0, args.length);
        int compressed_size = 0;
        int decompressed_size = 0;
        String current = "";
        while(true) {
            String operation = nargs[0];
            if (operation.equals("Encoding")) {
                String inputFile = nargs[1];
                System.out.println("In process . . .");
                try {
                    current = inputFile;
                    Encoder.compress(inputFile, "src/encoded.bin", compressed_size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Successfully!");
            }
            if (operation.equals("Decoding")) {
                String outputFile = nargs[1];
                System.out.println("In process . . .");
                try {
                    Decoder.decompress("src/encoded.bin", outputFile, decompressed_size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Successfully!");
            }
            if (operation.equals("Informing")) {
                Informer.getInfo(compressed_size, decompressed_size, current);
            }
        }
    }
}