package org.example;


import io.qameta.allure.Epic;
import org.example.groupeLO.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;

import static org.openqa.selenium.By.*;

/**
/* Автоматизация тестирования Web UI на Java. Homework 7.
/* @author Afanasyeva MS
*/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GroupeLOTest extends AbstractTest{

    static Cookie cookie;
    private static Logger logger = (Logger) LoggerFactory.getLogger(GroupeLOTest.class);

    @Test
    @Order(1)
    @Epic("Аутентификация")
    @DisplayName("Тест 1. Авторизация")
    void testLogin() {
        new GroupeLOMainPage(getWebDriver()).goToLoginPage();
        Assertions.assertTrue(getWebDriver().getTitle().equals("Вход на сайт GroupLe GroupLe"), "Страница входа на сайт недоступна");
        new LoginPage(getWebDriver()).loginIn( "Kusemos", "12TeSt468");
        cookie = getWebDriver().manage().getCookieNamed("remember_me");
        Assertions.assertDoesNotThrow(() -> getWebDriver().findElement(className("dropdown-toggle")),"Не удалось авторизоваться на сайте");
    }

    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.Random.class)
    class testWithAuth {

        @BeforeEach
        void initMainPage() {
            getWebDriver().manage().addCookie(cookie);
            getWebDriver().navigate().refresh();
            Assertions.assertDoesNotThrow(() -> getWebDriver().findElement(className("dropdown-toggle")),"Не удалось авторизоваться на сайте");
        }

        @Test
        @Epic("Работа с постами")
        @DisplayName("Тест 2. Создание черновика поста")
        void testAddPost() {
            new GroupeLOMainPage(getWebDriver()).navigateMainMenuPosts();
            Assertions.assertTrue(getWebDriver().getTitle().equals("Самые горячие посты GroupLe"), "Страница самых горячих постов недоступна");
            new PostsPage(getWebDriver()).navigateAddPost();
            Assertions.assertTrue(getWebDriver().getTitle().equals("Редактирование поста GroupLe"), "Страница редактирования поста недоступна");
            new PostPage(getWebDriver()).editPost("Тестировщики тестируют 3", "Тестировщики учатся тестировать 3");
            Assertions.assertTrue(getWebDriver().findElement(cssSelector(".noty_message")).getText().equals("saved"),"Пост не сохранен");
        }

        @Test
        @Epic("Работа с постами")
        @DisplayName("Тест 3. Удаление черновика поста")
        void testDelPost() {
            new GroupeLOMainPage(getWebDriver()).navigatePrivatePosts();
            Assertions.assertTrue(getWebDriver().getTitle().equals("Список ваших постов GroupLe"), "Страница ваших постов недоступна");
            new PrivatePosts(getWebDriver()).navigateToLastPost();
            Assertions.assertTrue(getWebDriver().getTitle().equals("Редактирование поста GroupLe"), "Страница редактирования поста недоступна");
            new PostPage(getWebDriver()).deletePost();
            Assertions.assertThrows(NoSuchElementException.class, () -> {
                getWebDriver().findElement(cssSelector(".noty_messages"));
            });
            new WebDriverWait(getWebDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("post/list"));
            Assertions.assertTrue(getWebDriver().findElement(xpath("//table/tbody/tr[1]/td[2]/span")).getText().equals("удалено"),"Пост не удален");
        }

        @Test
        @Epic("Поиск FindAnime")
        @DisplayName("Тест 4. Переход на страницу просмотра аниме")
        void testFunnyCat() {
            new GroupeLOMainPage(getWebDriver()).navigateMainMenuAnime();
            Assertions.assertTrue(getWebDriver().getTitle().equals("Смотреть аниме и мультики онлайн - FindAnime"), "Страница FindAnime.net недоступна");
            new FindAnimeMainPage(getWebDriver()).searchAnime("Я,");
            Assertions.assertTrue(getWebDriver().getCurrentUrl().equals("https://findanime.net/ia__cusima"), "Не удалось найти мультик");
            new AnimePage(getWebDriver()).navigateToLooking();
            Assertions.assertTrue(getWebDriver().getCurrentUrl().equals("https://findanime.net/ia__cusima/series1"), "Не удалось посмотреть мультик");
        }
    }
}
