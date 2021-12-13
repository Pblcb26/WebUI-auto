import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Lection3!
 */

public class App {
    public static void main( String[] args )  {
       /* WebdriverИмябраузера
       System.setProperty(
                "webdriver.chrome.driver", "src/main/resources/chromedriver.exe"
        );

        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");*/

        //debug evaluate


        //WebDriverManager - для всех браузеров
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        //options.addArguments("--headless"); - тесты без запуска браузера

        WebDriver driver = new ChromeDriver(options);

        //неявное ожидание. Макс ожидание для каждого действия.
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //TimeUnit - устарело

        //Приостановка. Отработает весь таймер
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.get("https://google.com");

        //driver.findElement(By.name("q"));
        //driver.findElements(By.name("12354")).isEmpty(); //есть ли элемент на странице? - если лист пуст - нет

        WebElement webElement = driver.findElement(By.name("q")); //нашли элемент
        //Явное ожидание. Ожидание для какой-либо операции.
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(webElement));
        webElement.click(); //кликнули (не всегда надо)
        webElement.sendKeys("Вики"); //ввели текст
        webElement.submit(); //отправили запрос

        //Скриншот
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit(); //драйвер сам не останавливается



    }
}
