import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class junit_01 {
    public static int test;

    @BeforeClass
    public static void init() {
        System.out.println("Start Testing...");
    }

    @Test
    public void test() {
        test = 1 + 1;
        Assert.assertEquals(2, test);
    }

    @Test
    public void test2() {
        test = 2 + 4;
        Assert.assertEquals(2, test);
    }

    @AfterClass
    public static void close() {
        System.out.println("Finish Testing.");
    }

}