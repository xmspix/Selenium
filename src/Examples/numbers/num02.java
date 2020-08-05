import java.text.NumberFormat;

public class num02 {
    public static void main(final String[] args) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String res = currency.format(123456.123);
        System.out.println(res);
    }
}