import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class yad2 {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "./tmp/chromedriver");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.yad2.co.il");

            // Click car menu
            driver.findElement(By.linkText("רכב")).click();

            // Click car price
            driver.findElement(By.linkText("מחירון רכב")).click();

            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

            // change focus to new tab
            driver.switchTo().window(tabs.get(1));

            if (driver.getCurrentUrl().contains("http://pricelist.yad2.co.il/")) {

                // Select Private
                driver.findElement(By.cssSelector("#CarType > option:nth-child(2)")).click();
                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

                // Select Audi
                driver.findElement(By.cssSelector("#Manufactur > option:nth-child(2)")).click();
                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

                // Select A4
                driver.findElement(By.cssSelector("#CarModel > option:nth-child(6)")).click();
                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

                // Select 2000
                driver.findElement(By.cssSelector("#Year > option:nth-child(23)")).click();
                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

                // Click Search
                driver.findElement(By.className("search_show_price_btn")).click();

                // Print first car price
                System.out.println(driver.findElement(By.cssSelector(
                        "#pricelist_leftcolumn > div.resultsBody.pie.bind > div.aResult.lastOne.open.showPopupUnder > div.submodelsList > div:nth-child(1) > table > tbody > tr > td:nth-child(3) > b"))
                        .getText());

                driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

                // Save Screenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(source, new File("./src/Examples/02/audi.png"));

                driver.close();
            }

            driver.switchTo().window(tabs.get(0));
            if (driver.getCurrentUrl().contains("https://www.yad2.co.il/")) {
                System.out.println(driver.getCurrentUrl());
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        driver.close();
    }
}
