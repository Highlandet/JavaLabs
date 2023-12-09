public class Main {
    public static void main(String[] args) {
        // Пример использования метода
        GenericClass<Integer> objA = new GenericClass<>(5);
        GenericClass<Integer> objB = new GenericClass<>(10);

        System.out.println("Исходные значения:");
        System.out.println("objA: " + objA.getValue());
        System.out.println("objB: " + objB.getValue());

        // Обмен значениями
        GenericClass.exchangeValues(objA, objB);

        System.out.println("\nЗначения после обмена:");
        System.out.println("objA: " + objA.getValue());
        System.out.println("objB: " + objB.getValue());
    }
}