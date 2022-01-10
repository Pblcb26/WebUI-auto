package groupeLO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GroupeLOMainPage extends AbstractPage{

    @FindBy (xpath = "//a[@href='/internal/auth/login'][@class='strong reg hidden-xs']")
    private WebElement login;

    @FindBy (className = "dropdown-toggle")
    private WebElement user;

    @FindBy (xpath = "//div[@class]/a[@href='/posts/sort_hot']")
    private WebElement mainMenuPosts;

    @FindBy (xpath = "//div[@class='pull-right strong']/span/a[@href='https://findanime.net']")
    private WebElement mainMenuAnime;

    @FindBy (xpath = "//a[@href='/private/post/list']")
    private WebElement privatePosts;

    public GroupeLOMainPage(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage() {
        this.login.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("grouple.co/internal/auth/login"));
    }

    public void navigateMainMenuPosts() {
        this.mainMenuPosts.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("posts/sort_hot"));
    }

    public void navigateMainMenuAnime() {
        this.mainMenuAnime.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("findanime.net"));
    }

    public void navigatePrivatePosts() {
        this.privatePosts.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/private/post/list"));
    }
}