import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Decode {
    public static Map<Character, String> readCodeFile(String fileName) {
        Map<Character, String> codeTable = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" : ");
                if (parts.length == 2) {
                    char symbol = parts[0].charAt(0);
                    String code = parts[1];
                    codeTable.put(symbol, code);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return codeTable;
    }
    public static void decode_process(String file_to_write, Map<Character, String> codeTable){
        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/keren/IdeaProjects/Huffman/src/encoded.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter(file_to_write))) {

            StringBuilder encodedMessage = new StringBuilder();
            int c;

            // Чтение закодированного сообщения из файла
            while ((c = br.read()) != -1) {
                encodedMessage.append((char) c);
            }

            // Декодирование сообщения
            StringBuilder currentCode = new StringBuilder();
            StringBuilder decodedMessage = new StringBuilder();

            for (char bit : encodedMessage.toString().toCharArray()) {
                currentCode.append(bit);

                // Проверяем, есть ли текущий код в таблице кодов
                for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
                    if (entry.getValue().equals(currentCode.toString())) {
                        // Найден соответствующий символ, добавляем его к декодированному сообщению
                        decodedMessage.append(entry.getKey());
                        // Сбрасываем текущий код
                        currentCode.setLength(0);
                        break;
                    }
                }
            }

            // Запись раскодированного сообщения в файл
            bw.write(decodedMessage.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void decode()
    {
        Map<Character, String> codeTable = readCodeFile("C:/Users/keren/IdeaProjects/Huffman/src/codetable.txt");
        System.out.println("Please, enter the path to the file to write decoded message");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        decode_process(filePath, codeTable);
    }
}
