package groupeLO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PostsPage extends AbstractPage{

    @FindBy (css = ".add.link-icon")
    private WebElement addPost;

    public PostsPage(WebDriver driver) {
        super(driver);
    }

    public void navigateAddPost() {
        this.addPost.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("private/post/edit?id"));
    }
}