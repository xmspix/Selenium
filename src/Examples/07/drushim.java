/*
    Drushim - Sending CV on autopilot
    Built by Mark Stoler - mark.stoler.86@gmail.com
    2020
*/

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class drushim {
    public static WebDriver driver;
    public static int curPage = 1;
    public static Boolean nextPage = true;
    public static String chromeDir = "./tmp/chromedriver"; // change chrome driver location
    public static String user = ""; // user name / email
    public static String password = "";  // password

    public static void goToOldSite() {
        // Check if new site is loaded then switching back to old one
        if (driver.findElements(By.xpath("//span[text()='חזרה לאתר הישן']")).size() >= 1)
            driver.findElement(By.xpath("//span[text()='חזרה לאתר הישן']")).click();
    }

    public static void login() {

        try {
            driver.manage().window().maximize();
            driver.get("https://www.drushim.co.il/");

            driver.findElement(By.xpath("//*[@id='Authentication_UnauthenticatedView']/a[1]")).click();

            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            goToOldSite();

            driver.findElement(By.className("login-email")).sendKeys(user);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.className("login-password")).sendKeys(password);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            driver.findElement(By.className("btnLogin")).click();

            goToOldSite();

            // Change this URL to your needs
            driver.get("https://www.drushim.co.il/jobs/cat24/area/1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17/?scope=1-2&advanced=1&experience=1-2");

            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loop() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            @SuppressWarnings("unchecked")
            // Filter jobs that CV have already been submitted + jobs that must be marked in the checkbox
            List<WebElement> jobsList = (List<WebElement>) js.executeScript("return [...document.querySelectorAll('.jobContainer')].filter(e => !e.innerHTML.includes('cvSentMessage') && !e.innerHTML.includes('filterWarning'))");

            System.out.printf("Page: %s, Jobs available: %s \n", curPage, jobsList.size());

            for (int i = 0; i < jobsList.size(); i++) {
                jobsList.get(i).click();

                driver.findElements(By.linkText("שלח קורות חיים")).get(i).click();

                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

                List<WebElement> btnSend = driver.findElements(By.cssSelector("#cboxLoadedContent > .sendCVPopup > .sendCVForm > div[rowid='-1'] > .btnContainer > a"));

                if (btnSend.size() > 0) {
                    btnSend.get(0).click();
                    System.out.println("btn available & clicked");
                    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                    driver.findElement(By.id("cboxClose")).click();
                } else {
                    driver.findElement(By.id("cboxClose")).click();
                    System.out.println("btn not available & exit");
                }

            }

            if (driver.findElements(By.linkText("הבא »")).size() == 0) {
                nextPage = false;
                driver.close();
            } else {
                curPage++;
                driver.findElement(By.linkText("הבא »")).click();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", chromeDir);
        driver = new ChromeDriver();

        login();

        while (nextPage) {
            loop();
        }

    }
}