import io.github.bonigarcia.wdm.WebDriverManager;
import groupeLO.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class GroupeLO {
    public static void main(String[] args) {
        WebDriver webDriver;

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(20));
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        GroupeLOMainPage groupeLOMainPage = new GroupeLOMainPage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        PostPage postPage = new PostPage(webDriver);
        PostsPage postsPage = new PostsPage(webDriver);
        PrivatePosts privatePosts = new PrivatePosts(webDriver);
        FindAnimeMainPage findAnimeMainPage = new FindAnimeMainPage(webDriver);
        AnimePage animePage = new AnimePage(webDriver);

        webDriver.get("https://grouple.co/");
        groupeLOMainPage.goToLoginPage();
        loginPage.loginIn("Kusemos", "12TeSt468");
        groupeLOMainPage.navigateMainMenuPosts();
        postsPage.navigateAddPost();
        postPage.editPost("gjhgfyj", "nvksjhgffskjhgbnsklgjbhnskjgfnd");
        webDriver.get("https://grouple.co/");
        groupeLOMainPage.navigatePrivatePosts();
        privatePosts.navigateToLastPost();
        postPage.deletePost();
        webDriver.get("https://grouple.co/");
        groupeLOMainPage.navigateMainMenuAnime();
        findAnimeMainPage.searchAnime("Я,");
        animePage.navigateToLooking();
        webDriver.quit();
    }
}