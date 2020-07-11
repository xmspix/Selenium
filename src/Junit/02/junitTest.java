import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class junitTest {

    public static WebDriver driver;
    public static String title, src;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @BeforeClass
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "/Users/markstoler/Documents/QA/java/Selenium/tmp/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.yad2.co.il");
    }

    @Test
    public void test1() {
        title = driver.getTitle();
        // System.out.println(title);
        Assert.assertEquals("לוח יד2 - דירות להשכרה, למכירה, רכב, דרושים, יד שנייה", title);
    }

    @AfterClass
    public static void close() {
        driver.quit();
    }

}
