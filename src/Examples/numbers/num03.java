import java.text.NumberFormat;

public class num03 {
    public static void main(final String[] args) {
        String res = NumberFormat.getPercentInstance().format(0.12);
        System.out.println(res);
    }
}