import java.util.Scanner;

public class input01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name:");
        String name = scanner.nextLine().trim();
        System.out.println("you are " + name);
        scanner.close();
    }
}