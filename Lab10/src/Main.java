public class Main {
    public static void main(String[] args) {
        Triple<Integer> triple = new Triple<>(5, 10, 3);
        System.out.println("Min: " + triple.min());
        System.out.println("Max: " + triple.max());
        System.out.println("Mean: " + triple.mean());
    }
}