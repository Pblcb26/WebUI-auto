package org.example.groupeLO;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class AbstractTest {

    static WebDriver webDriver;

    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(20));
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @BeforeEach
    void initMainPage() {
            Assertions.assertDoesNotThrow(() -> webDriver.get("https://grouple.co/"),
            "Страница недоступна");
    }

    @AfterEach
    void clearCookie(){
        webDriver.manage().deleteAllCookies();
    }

    @AfterAll
    static void webDriverQuit() {
        if (webDriver != null) webDriver.quit();
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }
}
