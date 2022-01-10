package org.example.groupeLO;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;
import java.util.List;

public abstract class AbstractTest {

    static WebDriver webDriver;
    static EventFiringWebDriver eventDriver;

    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(20));
        eventDriver = new EventFiringWebDriver(new ChromeDriver(options));
        eventDriver.register(new MyWDEventListener());
        eventDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @BeforeEach
    @Step ("Переход на главную страницу")
    void initMainPage() {
            Assertions.assertDoesNotThrow(() -> getWebDriver().get("https://grouple.co/"),
            "Страница недоступна");
    }

    @AfterEach
    void afterEach(){
        getWebDriver().manage().deleteAllCookies();

        List<LogEntry> allLogRows = getWebDriver().manage().logs().get(LogType.BROWSER).getAll();
        if (!allLogRows.isEmpty()) {
            if (allLogRows.size() > 0) {
                allLogRows.forEach(logEntry -> {
                    System.out.println(logEntry.getMessage());
                });
            }
        }
        Assertions.assertTrue(true);
    }

    @AfterAll
    static void webDriverQuit() {
        if (getWebDriver() != null) getWebDriver().quit();
    }

    public static WebDriver getWebDriver() {
        return eventDriver;
    }
}
