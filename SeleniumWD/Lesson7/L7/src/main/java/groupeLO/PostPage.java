package groupeLO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PostPage extends AbstractPage{

    @FindBy (id = "title")
    private WebElement title;

    @FindBy (css = ".note-editable")
    private WebElement textbox;

    @FindBy (id = "publicPost")
    private WebElement publicCheckbox;

    @FindBy (css = ".btn-info")
    private WebElement savePost;

    @FindBy (css = ".btn-danger")
    private WebElement deletePost;

    public PostPage(WebDriver driver) {
        super(driver);
    }

    public void editPost(String title, String text) {
        Actions editPost = new Actions(getDriver());
        editPost.sendKeys(this.title, title).sendKeys(this.textbox, text).click(this.publicCheckbox).click(this.savePost).build().perform();
    }

    public void deletePost() {
        this.deletePost.click();
        getDriver().switchTo().alert().accept();
    }
}