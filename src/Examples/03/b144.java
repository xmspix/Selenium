import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import atu.testrecorder.ATUTestRecorder;

public class b144 {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "./tmp/chromedriver");
        WebDriver driver = new ChromeDriver();
        ATUTestRecorder recorder = new ATUTestRecorder("./src/Examples/03", "TestVideo-bzq-login", false);

        try {
            recorder.start();
            driver.get("http://www.b144.co.il");
            driver.findElement(By.className("login")).click();
            driver.findElement(By.id("txtUserName")).sendKeys("Test");
            driver.findElement(By.id("txtPassword")).sendKeys("Test");
            recorder.stop();

        } catch (Exception e) {
            System.out.println(e);
        }

        driver.close();
    }
}