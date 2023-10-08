import java.util.*;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;
class Node {
    private int parent;
    private int left;
    private int right;
    private int probability;
    private char symbol;
    private String code;
    private boolean merged;

    public Node(int probability, char symbol) {
        this.parent = -1;
        this.right = -1;
        this.left = -1;
        this.probability = probability;
        this.symbol = symbol;
        this.code = "";
        this.merged = false;
    }

    public void setChildren(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void merging() {
        this.merged = true;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public int getRight() {
        return this.right;
    }

    public int getLeft() {
        return this.left;
    }

    public int getProbability() {
        return this.probability;
    }

    public int getParent() {
        return this.parent;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isMerged() {
        return this.merged;
    }
}
public class HuffmanEncode {
    public static String createInputString() {
        StringBuilder inputString = new StringBuilder();
        try {
            Path filePath = Paths.get("src", "decoded.txt");
            File file = filePath.toFile();
            FileReader fileReader = new FileReader(file);
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
            if (sym != ' ')
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
                System.out.println(nodeList.get(i).getSymbol() + " " + nodeList.get(i).getProbability()+ " " + nodeList.get(i).getCode());
            }
            if (nodeList.get(nodeList.get(i).getParent()).getRight() == i) {
                nodeList.get(i).setCode(nodeList.get(nodeList.get(i).getParent()).getCode() + "1");
                System.out.println(nodeList.get(i).getSymbol() + " " + nodeList.get(i).getProbability()+ " " + nodeList.get(i).getCode());
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
    public static String encode(String initial, Map<Character, String> codeTable) {
        StringBuilder readyFor = new StringBuilder();
        for (char letter : initial.toCharArray()) {
            if (letter != ' ')
                readyFor.append(codeTable.get(letter));
            else
                readyFor.append(" ");
        }
        writeInFile(readyFor.toString());
        return readyFor.toString();
    }
}
