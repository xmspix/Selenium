import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class linkedin {
    public static String user = ""; // add username
    public static String pass = ""; // add password
    public static String search = "qa manager";
    public static int countPeople = 0;
    public static int page = 1;
    public static int minPeopleToAdd = 90;

    public static WebDriver driver;
    public static JavascriptExecutor js;

    public static void login() {
        try {
            // driver = new ChromeDriver();
            driver.get("https://www.linkedin.com/login?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");
            driver.manage().window().maximize();

            driver.findElement(By.cssSelector("#username")).sendKeys(user);
            driver.findElement(By.cssSelector("#password")).sendKeys(pass);
            driver.findElement(By.cssSelector(
                    "#app__container > main > div:nth-child(2) > form > div.login__form_action_container > button"))
                    .click();

            driver.findElement(By.cssSelector(".search-global-typeahead__input.always-show-placeholder"))
                    .sendKeys('"' + search + '"' + Keys.RETURN);

            driver.findElement(By.xpath("//span[text()='People']")).click();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void connect() {
        js = (JavascriptExecutor) driver;
        List<WebElement> connections;
        try {
            Thread.sleep(2000);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // Scroll to end of page
            Thread.sleep(2000);
            js.executeScript("window.scrollBy(0, 0)"); // Scroll to top
            Thread.sleep(1000);

            connections = driver.findElements(By.xpath("//button[text()='Connect']"));

            // WebElement _page = driver.findElement(By.cssSelector("li.selected >
            // button"));
            // int _pageNum = Integer.parseInt(_page.getAttribute("childElementCount"));

            System.out.printf("Page: %s, Connections on page: %s \n\n", page, connections.size());

            if (connections.size() == 0) {
                driver.findElements(By.xpath("//button[@aria-label='Next']")).get(0).click();
                page = page + 1;
            } else {
                for (int i = 0; i < connections.size(); i++) {
                    try {
                        connections.get(i).click();
                        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                        driver.findElements(By.xpath("//span[text()='Done']")).get(0).click();
                        countPeople = countPeople + 1;
                    } catch (Exception e) {
                        System.out.println("Something goes wrong, can't add this person");
                    }
                    if (i == connections.size() - 1) {
                        System.out.printf("\n Pages so far: %s ,Added connections so far: %s \n", page, countPeople);
                        driver.findElements(By.xpath("//button[@aria-label='Next']")).get(0).click();
                        page = page + 1;
                    }
                }

                // System.out.printf("\n Pages so far: %s ,Added connections so far: %s \n",
                // page, countPeople);
                // driver.findElements(By.xpath("//button[@aria-label='Next']")).get(0).click();
                // page = page + 1;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "./tmp/chromedriver");
        driver = new ChromeDriver();

        login();

        while (countPeople <= minPeopleToAdd) {
            connect();
        }

        driver.close();
    }

}