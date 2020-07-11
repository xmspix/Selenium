import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class migdal {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "./tmp/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.migdal.co.il");

            driver.findElement(By.cssSelector(
                    "body > header > div > div.headerUpper > div.headerLeft.bo-hidden > ul > li:nth-child(2) > a"))
                    .click();
            driver.findElement(By.linkText("קבלת הצעה")).click();
            driver.findElement(By.linkText("ביטוח רכב")).click();
            driver.findElement(By.linkText("המשך למילוי פניה")).click();

            driver.findElement(By.name("fullName")).sendKeys("פלוני אלמוני");
            driver.findElement(By.name("idNumber")).sendKeys("1235");
            driver.findElement(By.xpath("//*[@id='contactWizard_panel03']/div/form/div[2]/div[2]/div/ul/li[2]/input"))
                    .sendKeys("דואר אלקטרוני");
            driver.findElement(By.name("phoneNumber")).sendKeys("0501234567");

            // Check checkbox if not checked
            if (!driver.findElement(By.name("agreementMailing")).isSelected()) {
                driver.findElement(By.name("agreementMailing")).click();
            }

            // Look for ID error
            if (driver.findElement(By.cssSelector(
                    "#contactWizard_panel03 > div > form > div:nth-child(2) >div.contactWizard_formFrame > div > ul >li.contactWizard_form30.contactWizard_formError > div"))
                    .getText().contains("תעודת זהות לא תקינה")) {
                System.out.println("ID number is wrong");
            }

            // Look for email error
            if (driver.findElement(By.cssSelector(
                    "#contactWizard_panel03 > div > form > div:nth-child(2) >div.contactWizard_formFrame > div > ul >li.contactWizard_form50.contactWizard_formError > div"))
                    .getText().contains("כתובת מייל לא תקינה")) {
                System.out.println("email is wrong");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        driver.close();

    }
}