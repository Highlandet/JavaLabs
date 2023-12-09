public class Main {
    public static void main(String[] args) {
        // Логические операции
        boolean a = true;
        boolean b = false;

        System.out.println("Логическое И (&&): " + (a && b));
        System.out.println("Логическое ИЛИ (||): " + (a || b));
        System.out.println("Логическое НЕ (!a): " + (!a));

        // Тернарная операция
        int x = 5;
        int y = 10;
        int min = (x < y) ? x : y;
        System.out.println("Минимальное значение: " + min);

        // Поразрядные логические операции
        int num1 = 5;  // 0101 в 2СС
        int num2 = 3;  // 0011 в 2СС

        System.out.println("Поразрядное И (&): " + (num1 & num2));  // Результат: 0001 (1 в 10СС)
        System.out.println("Поразрядное ИЛИ (|): " + (num1 | num2));  // Результат: 0111 (7 в 10СС)
        System.out.println("Поразрядное исключающее ИЛИ (^): " + (num1 ^ num2));  // Результат: 0110 (6 в 10СС)
        System.out.println("Поразрядное НЕ (~) для num1: " + (~num1));  // Результат: 11111111111111111111111111111010 (в 32 битах)

        // Операции сдвига
        int num3 = 16;  // 0001 0000 в двоичной системе

        System.out.println("Сдвиг вправо на 2 позиции (>>): " + (num3 >> 2));  // Результат: 0000 0100 (4 в 10СС)
        System.out.println("Сдвиг влево на 3 позиции (<<): " + (num3 << 3));  // Результат: 1000 0000 (128 в 10С)
        System.out.println("Логический сдвиг вправо на 2 позиции (>>>): " + (num3 >>> 2));  // Результат: 0000 0100 (4 в 10СС)
    }
}