import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Decoder {
    public static byte[] decompress(String inputFile, String outputFile, int decompressed_size) throws FileNotFoundException {
        HashMap<String, Byte> recHash= new HashMap<>();
        String headerData = "";
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            headerData += br.readLine();
            if (!headerData.equals("P O L Y H U F F M A N"))
                throw new UnsupportedOperationException("Wrong file format . . .");
            headerData += '\n';
            String recHashSize = br.readLine();
            System.out.println(recHashSize);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        File compressedFile = new File(inputFile);

        String compressed = "";
        int originalLength = 0;
        try { //ТУТУТТУТУТУТУТУТ
            FileInputStream fis = new FileInputStream(compressedFile);
            fis.skip(headerData.getBytes().length);
            int cur;
            while ((cur = fis.read())!= -1) {
                originalLength++;
                String my_data = String.format("%8s", Integer.toBinaryString(((byte) cur) & 0xFF)).replace(" ", "0");
                //System.out.println(my_data);
                compressed += my_data;
            }
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        decompressed_size = originalLength;
        int extraZeros = Tree.getAdditionalZerosNumber(compressed);
        compressed = compressed.substring(8, compressed.length() - extraZeros);
        ArrayList<Byte> result = new ArrayList<>();
        String current = "";
        for (int index = 0; index < compressed.length(); index += 1) {
            current += compressed.charAt(index);
            if (recHash.containsKey(current)) {
                result.add(recHash.get(current));
                current = "";
            }
        }
        byte[] decompressed = new byte[result.size()];
        for (int i = 0; i < result.size(); i++) {
            decompressed[i] = result.get(i);
            System.out.println((char)((byte) result.get(i)));
        }
        System.out.println(decompressed);
        FileOutputStream fos = new FileOutputStream(outputFile);
        try {
            fos.write(decompressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decompressed;
    }
}
