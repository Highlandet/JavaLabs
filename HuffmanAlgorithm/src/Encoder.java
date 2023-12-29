import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Encoder {
    public static void compress(String inputFile, String outputFile, int compressed_size) throws IOException {
        HashMap<Byte, Integer> freqMap = new HashMap<>();
        byte[] data = Tree.readFile(inputFile);
        compressed_size = data.length;
        Tree huffman = new Tree();
        freqMap = huffman.getFreqMap(data);
        Vertex root = huffman.buildTree(freqMap);
        huffman.generateCodes(root, "");
        HashMap<Byte, String> codesHash = huffman.getCodes();
        FileOutputStream fos = new FileOutputStream(outputFile, true);
        String header = "P O L Y H U F F M A N" + "\n";
        header += Integer.toString(codesHash.size()) + "\n";
        for (Map.Entry<Byte, String> entry : codesHash.entrySet()) {
            Byte symbol = entry.getKey();
            String code = entry.getValue();
            header += (char) (symbol & 0xFF) + code + '\n';
        }
        try(PrintWriter pw = new PrintWriter(outputFile)) {
            pw.println(header);
        } catch (FileNotFoundException e) {
            System.err.println("Failed writing in file . . .");
        }
        byte[] result = Tree.encode(data);
        fos.write(result);
        fos.close();
        System.out.println("Output-File path is: " + outputFile);
    }
}
