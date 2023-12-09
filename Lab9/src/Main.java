import java.sql.Ref;

public class Main {
    public static void main(String[] args) {
        try {
            // Создание экземпляра класса по имени
            String className = "MyClass";
            Object instance = Reflection.createInstance(className);

            String methodName = "print";
            Reflection.invokeMethod(instance, methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}