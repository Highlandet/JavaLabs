import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Inform {
    private static int count_entries() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/keren/IdeaProjects/Huffman/src/codetable.txt"));
        int line_count = 0;
        String line;
        while((line = reader.readLine()) != null) {
            line_count++;
        }
        return line_count;
    }
    private static int countSymbols(String filePath) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int currentChar;

            while ((currentChar = reader.read()) != -1) {
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
    public static void inform() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("With which file you want compare encoded text?\nPlease, enter its path.");
        String path_to_decoded = scanner.nextLine();
        try {
            int symbol_amount_decoded = countSymbols(path_to_decoded);
            int code_table_size = count_entries();
            int info_volume_per_symbol = (int) Math.ceil(Math.log((double) code_table_size) / Math.log(2));
            int info_volume_decoded = info_volume_per_symbol * symbol_amount_decoded;
            int symbol_amount_encoded = countSymbols("C:/Users/keren/IdeaProjects/Huffman/src/encoded.txt");
            int info_volume_encoded = symbol_amount_encoded;
            double compression_degree = (double)(info_volume_decoded) / info_volume_encoded;
            System.out.println("Size of Decoded file: " + info_volume_decoded);
            System.out.println("Size of Encoded file: " + info_volume_encoded);
            System.out.println("Compression degree: " + compression_degree + "\n");
        } catch (IOException e) {
            System.out.println("Unable to count: " + e.getMessage());
        }
    }
}
