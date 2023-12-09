public class Main {
    public static void main(String[] args)
    {
        // concat(): объединение строк
        String str1 = "Hello!";
        String str2 = "Hello, World";
        String concatenated = str1.concat(str2);
        System.out.println("concatenated: " + concatenated);

        // valueOf(): преобразование объекта в строку
        int number = 234;
        String strFromNumber = String.valueOf(number);
        System.out.println("strFromNumber: " + strFromNumber);

        // join(): соединение строк с учетом разделителя
        String[] phrase = {str1, str2};
        String joinedString = String.join(" ", phrase);
        System.out.println("joinedString: " + joinedString);

        // equals(): сравнение строк с учетом регистра
        boolean areEqual = str1.equals(str2);
        System.out.println("areEqual: " + areEqual);

        // equalsIgnoreCase(): сравнение строк без учета регистра
        boolean areEqualIgnoreCase = str1.equalsIgnoreCase("HELLO");
        System.out.println("areEqualIgnoreCase: " + areEqualIgnoreCase);

        // compareTo(): сравнение двух строк
        int comparisonResult = str1.compareTo(str2);
        System.out.println("comparisonResult: " + comparisonResult);

        // regionMatches(): сравнение подстрок в строках
        boolean regionMatches = str1.regionMatches(0, "HeLLo", 0, 5);
        System.out.println("regionMatches: " + regionMatches);

        // startsWith(): определяет, начинается ли строка с подстроки
        boolean startsWith = str1.startsWith("He");
        System.out.println("startsWith: " + startsWith);

        // endsWith(): определяет, заканчивается ли строка на подстроку
        boolean endsWith = str1.endsWith("lo");
        System.out.println("endsWith: " + endsWith);

        // replace(): заменяет в строке одну подстроку на другую
        String replacedString = str1.replace("Hello", "Hi");
        System.out.println("replacedString: " + replacedString);

        // trim(): удаляет начальные и конечные пробелы
        String stringWithSpaces = " Java Programming ";
        String trimmedString = stringWithSpaces.trim();
        System.out.println("trimmedString: " + trimmedString);

        // substring(): возвращает подстроку
        String subString = str2.substring(7);
        System.out.println("subString: " + subString);

        // charAt(): возвращает символ строки по индексу
        char character = str1.charAt(0);
        System.out.println("character: " + character);

        // indexOf(): нахождение индекса первого вхождения подстроки
        int indexOfSubstring = str2.indexOf("o");
        System.out.println("indexOfSubstring: " + indexOfSubstring);

        // lastIndexOf(): нахождение индекса последнего вхождения подстроки
        int lastIndexOfSubstring = str2.lastIndexOf("o");
        System.out.println("lastIndexOfSubstring: " + lastIndexOfSubstring);

        // toLowerCase(): перевод всех символов строки в нижний регистр
        String lowerCaseString = str2.toLowerCase();
        System.out.println("lowerCaseString: " + lowerCaseString);

        // toUpperCase(): перевод всех символов строки в верхний регистр
        String upperCaseString = str2.toUpperCase();
        System.out.println("upperCaseString: " + upperCaseString);

    }
}