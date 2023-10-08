import java.util.*;

public class Main {

    public static void main(String[] args) {
        String inputString = HuffmanEncode.createInputString();
        Map<Character, Integer> symbolDictionary = HuffmanEncode.writeSymbols(inputString);
        List<Node> nodeList = HuffmanEncode.createNodes(symbolDictionary);
        Map<Character, String> codeTable = HuffmanEncode.huffmanTree(nodeList);
        System.out.println(HuffmanEncode.encode(inputString, codeTable));
    }
}
