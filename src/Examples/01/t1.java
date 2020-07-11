import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class t1 {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "./tmp/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://danielauto.net/practice/locator/locator.html");

        System.out.println(driver.findElement(By.id("by_id")).getText());
        System.out.println(driver.findElement(By.name("by_Name")).getText());
        System.out.println(driver.findElement(By.tagName("h3")).getText());
        System.out.println(driver.findElement(By.className("c_name")).getText());
        System.out.println(driver.findElement(By.xpath("/html/body/form/p[6]")).getText());
        driver.findElement(By.linkText("Click me to check partial link text")).click();
        System.out.println(driver.findElement(By.id("p_link_text")).getText());
        driver.navigate().back();
        driver.findElement(By.cssSelector("body > form > p:nth-child(11) > input[type=button]")).click();
        System.out.println(driver.findElement(By.id("btn")).getText());
        driver.close();

    }
}
