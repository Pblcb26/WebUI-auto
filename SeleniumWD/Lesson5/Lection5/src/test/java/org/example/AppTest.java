package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;


/**
 * Unit test for simple App.
 */
public class AppTest {

    static WebDriver webDriver;

    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(10));
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeEach
    void initMainPage() {
        Assertions.assertDoesNotThrow(() -> webDriver.navigate().to("https://www.bbc.com/"),
                "Страница недоступна");
    }

    @Test
    void  test1(){
        WebElement webElement = webDriver.findElement(By.id("id")); //для повторных использований объекта
        webElement.click();

        Actions actions = new Actions(webDriver);
        actions.sendKeys(webElement, "12345");

        webDriver.findElement(By.id("id")).click(); //короткая запись, если элемент используется мало

        Actions actions1 = new Actions(webDriver);
        actions1.sendKeys(webDriver.findElement(By.id("id")), "12345").click(webElement)
                .sendKeys(webDriver.findElement(By.id("id"))).sendKeys(webDriver.findElement(By.id("id")))
                .build().perform();

        System.out.println(webDriver.getWindowHandle());
        //assert(webDriver.getWindowHandles().size() == 1); //проверка кол-ва открытых окон
    }

    @AfterEach
    void exit() {
        webDriver.quit();
    }
}
