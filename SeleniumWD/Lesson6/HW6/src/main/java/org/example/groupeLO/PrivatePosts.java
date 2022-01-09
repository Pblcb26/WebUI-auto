package org.example.groupeLO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PrivatePosts extends AbstractPage{

    @FindBy (xpath = "//table/tbody/tr[1]/td[1]/a")
    private WebElement lastPostEdit;

    public PrivatePosts(WebDriver driver) {
        super(driver);
    }

    public void navigateToLastPost() {
        this.lastPostEdit.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("private/post/edit?id"));
    }
}