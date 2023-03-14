import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        if (!str.matches("^\\d+$") || BigDecimal.valueOf(Long.MAX_VALUE).compareTo(new BigDecimal(str)) < 0) {
            System.out.println("Ожидалось значение типа long [0, 2 ^ 63 - 1]");
        }
        else {
            printBytes(Long.parseLong(str));
        }

        scanner.close();
    }

    private static void printBytes(long number) {
        if (number < 0) {
            System.out.println("Некорректное значение!");
            // Эту проблему решает регулярное выражение, но тк метод потенциально
            // может быть вызван где угодно эту проверку следует проводить
        }
        else {
            // Всего 6 единиц измерения, тк long не превышает 2^63 - 1.
            final String[] measureUnits = new String[]{"", "K", "M", "G", "T", "P", "E"};
            byte count = 0;
            double nextNum = number;

            while (nextNum >= 1024) {
                nextNum /= 1024;
                count++;
            }

            String answer = String.format("%.1f %sB%n", nextNum, measureUnits[count]).replace(',', '.');
            System.out.println(answer);
        }
    }
}