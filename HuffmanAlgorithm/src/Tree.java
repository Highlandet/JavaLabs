import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Tree {

    public static byte[] readFile(String filePath)
    {
        File file = new File(filePath);
        byte[] data = new byte[(int) file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(data);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    static HashMap<Byte, String> CODES = new HashMap<>();
    public static byte[] encode(byte[] data) {
        String compressed = "";
        for(byte b : data) {
            compressed += CODES.get(b);
        }
        compressed = fillAdditionalZeros(compressed);
        StringBuilder compressedString = new StringBuilder(compressed);
        byte[] result = new byte[compressedString.length() / 8];
        for (int index = 0; index < result.length; index += 1) {
            result[index] = (byte) Integer.parseInt(compressedString.substring(index * 8, (index + 1) * 8), 2);
        }
        return result;
    }
    public HashMap<Byte, Integer> getFreqMap(byte[] data) {
        HashMap<Byte, Integer> freqMap = new HashMap<>();
        for (byte b : data) {
            Integer value = freqMap.get(b);
            if(value != null) {
                value += 1;
            } else {
                value = 1;
            }
            freqMap.put(b, value);
        }
        return freqMap;
    }
    public Vertex buildTree(HashMap<Byte, Integer> freqMap) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        for(byte key : freqMap.keySet()){
            pq.add(new Vertex(freqMap.get(key), key));
        }
        while (pq.size() > 1){
            Vertex left = pq.poll();
            Vertex right = pq.poll();
            Vertex parental = new Vertex(left.data+ right.data, (byte) 0);
            parental.left = left;
            parental.right = right;
            parental.status = true;
            pq.add(parental);
        }
        return pq.poll();
    }
    private static String fillAdditionalZeros(String compressed) {
        int delta = 8 - compressed.length() % 8;
        byte counter = 0;

        for (; counter < delta; counter += 1) {
            compressed += "0";
        }
        return String.format("%8s", Integer.toBinaryString(counter & 0xff)).replace(" ", "0") + compressed;
    };
    static public int getAdditionalZerosNumber(String compressed) {
        String delta = compressed.substring(0, 8);
        return Integer.parseInt(delta, 2);
    };

    public void generateCodes(Vertex root, String code)
    {
        if(root == null){
            return;
        }
        if(!root.status){
            CODES.put(root.symbol, code);
        }
        generateCodes(root.left, code + "0");
        generateCodes(root.right, code + "1");
    }

    public HashMap<Byte, String> getCodes()
    {
        return CODES;
    }

    public byte[] decode(String compressed, HashMap<String, Byte> recHash) {
        ArrayList<Byte> res = new ArrayList<>();
        String cur = "";

        for(int i = 0; i < compressed.length(); i++) {
            cur += compressed.charAt(i);
            if(recHash.containsKey(cur)) {
                res.add(recHash.get(cur));
                cur = "";
            }
        }

        byte[] decompressed = new byte[res.size()];
        for(int i = 0; i < res.size(); i++) {
            decompressed[i] = res.get(i);
        }
        return decompressed;
    }
    public static void printHash(String inputFile) {
        HashMap<String, Byte> recHash= new HashMap<>();
        String headerData = "";
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            br.readLine();
            String recHashSize = br.readLine();
            if(Integer.valueOf(recHashSize) == 0) {
                throw new Exception("There's no codetable . . .");
            }

            for(int i = 0; i < Integer.valueOf(recHashSize); i++) {
                char sym;
                String code;
                String entry = br.readLine();
                if(entry.length() == 0) {
                    sym = '\n';
                    code = br.readLine();
                    headerData += sym + code + '\n';
                } else {
                    sym = entry.charAt(0);
                    code = entry.substring(1, entry.length());
                    headerData += entry + '\n';
                }
                recHash.put(code, (byte) sym);
            }
            for(String i : recHash.keySet()) {
                System.out.println(recHash.get(i) + ":" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
