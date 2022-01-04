package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;
import static org.openqa.selenium.By.linkText;

/**
/* Автоматизация тестирования Web UI на Java. Homework 5.
/* @author Afanasyeva MS
*/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    static WebDriver webDriver;
    static Cookie cookieAuth;

    @BeforeEach
    void setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(20));
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    @DisplayName("Тест 1. Авторизация + Куки")
    @Order(1)
    void testLogin() {

        Assertions.assertDoesNotThrow(() -> webDriver.get("https://grouple.co/"),
                "Страница недоступна");

        Actions actions = new Actions(webDriver);
        webDriver.findElement(By.linkText("Вход")).click();
        actions.sendKeys(webDriver.findElement(By.id("username")), "Kusemos")
                .sendKeys(webDriver.findElement(By.id("password")), "12TeSt468")
                .click(webDriver.findElement(By.id("remember_me_yes")))
                .click(webDriver.findElement(By.cssSelector(".btn-success")))
                .build().perform();

        cookieAuth = webDriver.manage().getCookieNamed("remember_me");
        System.out.println(cookieAuth);

        String user = webDriver.findElement(linkText("Kusemos")).getText();
        Assertions.assertEquals("Kusemos", user, "Не удалось авторизоваться на сайте");
    }

    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.Random.class)
    class testWithAuth {

        @BeforeEach
        void initAuth() {
            Assertions.assertDoesNotThrow(() -> webDriver.get("https://grouple.co/"),
                    "Страница недоступна");
            webDriver.manage().addCookie(cookieAuth);
        }

        @Test
        //@Disabled
        @DisplayName("Тест 2. Создание черновика поста")
        void testAddPost() {

            Actions actions = new Actions(webDriver);
            webDriver.findElement(By.linkText("Посты")).click();

            String user = webDriver.findElement(linkText("Kusemos")).getText();
            Assertions.assertEquals("Kusemos", user, "Не удалось авторизоваться на сайте");

            webDriver.findElement(By.linkText("Написать пост")).click();
            actions.sendKeys(webDriver.findElement(By.id("title")),"Тестировщики тестируют2")
                    .sendKeys(webDriver.findElement(By.cssSelector(".note-editable")), "Тестировщики учатся тестировать2")
                    .click(webDriver.findElement(By.id("publicPost")))
                    .click(webDriver.findElement(By.cssSelector(".btn-info"))).build().perform();

            String title = webDriver.findElement(By.id("title")).getAttribute("value");
            Assertions.assertEquals("Тестировщики тестируют2", title, "Не удалось создать пост");
        }

        @Test
        //@Disabled
        @DisplayName("Тест 3. Удаление черновика поста")
        void testDelPost() {

            webDriver.findElement(By.linkText("Ваш блог")).click();

            String user = webDriver.findElement(linkText("Kusemos")).getText();
            Assertions.assertEquals("Kusemos", user, "Не удалось авторизоваться на сайте");

            webDriver.findElement(By.xpath("//a[@href='/private/post/edit?id=37790']")).click();
            webDriver.findElement(By.cssSelector(".btn-danger")).click();
            webDriver.switchTo().alert().accept();

            String status = webDriver.findElement(By.xpath("//a[@href='/private/post/edit?id=37790']/../../td/span[@class='label label-danger']")).getText();
            Assertions.assertEquals("удалено", status, "Не удалось удалить пост");
        }

        @Test
        @DisplayName("Тест 4. Переход на страницу просмотра аниме")
        void testFunnyCat() {

            webDriver.findElement(By.xpath("//div[@class='pull-right strong']/span/a[@href='https://findanime.net']")).click();
            webDriver.findElement(By.id("q-selectized")).sendKeys("Я,");
            webDriver.findElement(By.xpath("//div[@data-value='/ia__cusima']")).click();

            String title = webDriver.getTitle();
            Assertions.assertEquals("Аниме Я, Цусима (I'm Tsushima the Cat: Ore, Tsushima) онлайн - FindAnime", title, "Не удалось посмотреть мультик");

            webDriver.findElement(By.linkText("Смотреть аниме с первой серии")).click();

            String titleSeries1 = webDriver.getTitle();
            Assertions.assertEquals("Смотреть аниме Я, Цусима (I'm Tsushima the Cat: Ore, Tsushima) бесплатно и онлайн. Серия 1 с озвучкой или субтитрами - FindAnime", titleSeries1, "Не удалось посмотреть мультик");
        }
    }

    @AfterEach
    void exit() {
        webDriver.quit();
    }
}
