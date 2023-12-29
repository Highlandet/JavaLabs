public class Informer {
    public static void getInfo(int compressed_size, int decompressed_size, String inputFile){
        System.out.println("Code Table:");
        Tree.printHash(inputFile);
        System.out.println("Original File Size: " + decompressed_size);
        System.out.println("Compressed File Size: " + compressed_size);
        System.out.print("Compression Degree: ");
        System.out.printf("%.2f", ((double)(decompressed_size)/compressed_size - 1) * 100);
        System.out.println("%");
    }
}
