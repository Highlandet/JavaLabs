import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;
public class Encode {
    public static String createInputString(String filePath) {
        StringBuilder inputString = new StringBuilder();
        try {
            Path fileFullPath = Paths.get(filePath);
            FileReader fileReader = new FileReader(fileFullPath.toFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                inputString.append(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputString.toString();
    }

    public static Map<Character, Integer> writeSymbols(String str) {
        Map<Character, Integer> dictionary = new HashMap<>();
        for (char sym : str.toCharArray())
            dictionary.put(sym, dictionary.getOrDefault(sym, 0) + 1);
        return dictionary;
    }

    public static List<Node> createNodes(Map<Character, Integer> dictionary) {
        List<Node> nodeList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : dictionary.entrySet())
            nodeList.add(new Node(entry.getValue(), entry.getKey()));
        return nodeList;
    }

    public static Map<Character, String> huffmanTree(List<Node> nodeList) {
        int notSeen = nodeList.size();
        while (notSeen != 1) {
            int max = 0;
            for (int i = 0; i < nodeList.size(); i++)
                if (nodeList.get(max).getProbability() <= nodeList.get(i).getProbability())
                    max = i;
            int min1 = max;
            int min2 = max;
            if (nodeList.get(min2).getProbability() < nodeList.get(min1).getProbability()
                    && !nodeList.get(min2).isMerged()) {
                int tmp = min1;
                min1 = min2;
                min2 = tmp;
            }
            for (int i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i).getProbability() <= nodeList.get(min1).getProbability()
                        && !nodeList.get(i).isMerged()) {
                    min2 = min1;
                    min1 = i;
                } else if (nodeList.get(i).getProbability() <= nodeList.get(min2).getProbability()
                        && !nodeList.get(i).isMerged())
                    min2 = i;
            }
            nodeList.add(new Node(nodeList.get(min1).getProbability() + nodeList.get(min2).getProbability(), '.'));
            nodeList.get(nodeList.size() - 1).setChildren(min2, min1);
            nodeList.get(min1).setParent(nodeList.size() - 1);
            nodeList.get(min2).setParent(nodeList.size() - 1);
            nodeList.get(min1).merging();
            nodeList.get(min2).merging();
            notSeen--;
        }
        Map<Character, String> codeTable = new HashMap<>();
        nodeList.get(nodeList.size() - 1).setCode("");
        for (int i = nodeList.size() - 2; i >= 0; i--) {
            if (nodeList.get(nodeList.get(i).getParent()).getLeft() == i) {
                nodeList.get(i).setCode(nodeList.get(nodeList.get(i).getParent()).getCode() + "0");
            }
            if (nodeList.get(nodeList.get(i).getParent()).getRight() == i) {
                nodeList.get(i).setCode(nodeList.get(nodeList.get(i).getParent()).getCode() + "1");
            }
            if (nodeList.get(i).getSymbol() != '.')
                codeTable.put(nodeList.get(i).getSymbol(), nodeList.get(i).getCode());
        }
        return codeTable;
    }

    public static void writeInFile(String encodedString){
        try {
            String filePath = "src/encoded.txt"; // Укажите полный путь к файлу
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(encodedString);
            bufferedWriter.close();
            System.out.println("Закодированная строка записана в файл encoded.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeCodeTableInFile(Map<Character, String> codeTable, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/" + filename))) {
            for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
                String line = entry.getKey() + " : " + entry.getValue();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Таблица кодов записана в " + filename);
        }
    }
    public static String encode_process(String initial, Map<Character, String> codeTable) {
        StringBuilder readyFor = new StringBuilder();
        for (char letter : initial.toCharArray()) {
            //if (letter != ' ') ////////////
            //    readyFor.append(codeTable.get(letter));
            //else
            //    readyFor.append(" ");
            readyFor.append(codeTable.get(letter));
        }
        writeInFile(readyFor.toString());
        try {
            writeCodeTableInFile(codeTable, "codetable.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readyFor.toString();
    }
    public static void encode()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the path of file you want to encode: ");
        String filePath = scanner.nextLine();
        String inputString = createInputString(filePath);
        Map<Character, Integer> symbolDictionary = writeSymbols(inputString);
        List<Node> nodeList = createNodes(symbolDictionary);
        Map<Character, String> codeTable = huffmanTree(nodeList);
        System.out.println(encode_process(inputString, codeTable) + "\n");
    }
}