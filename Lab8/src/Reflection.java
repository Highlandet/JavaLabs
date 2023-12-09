import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Reflection {
    public static void getClassInformation(Class<?> clazz) {
        System.out.println("Info about " + clazz.getName());

        // Получение полей класса
        System.out.println("Fields:");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("  " + field.getType().getName() + " " + field.getName());
        }

        // Получение методов класса
        System.out.println("\nMethods:");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("  " + method.getReturnType().getName() + " " + method.getName() + "()");
        }

        // Получение конструкторов класса
        System.out.println("\nConstructors:");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.print("  " + clazz.getSimpleName() + "(");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                System.out.print(parameterTypes[i].getSimpleName());
                if (i < parameterTypes.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }

        System.out.println("Access modifier: " + clazz.getModifiers());
        System.out.println("Parent class: " + clazz.getSuperclass().getName());

        System.out.println("\n---------------------------------------\n");
    }
}
