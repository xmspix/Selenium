import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class hadarmorim {

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "./tmp/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://www.hadarmorim.co.il/article.asp?id=5225");

            System.out.println(
                    driver.findElement(By.className("headblackCopy1")).getText().contains("תהליך עיצוב והבנית הידע"));
            System.out.println(
                    driver.findElement(By.className("headblackCopy1")).getText().contains("אנציקלופדיה חישובית"));

        } catch (Exception e) {
            System.out.println(e);
        }
        driver.close();
    }
}