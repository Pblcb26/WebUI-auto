package org.example.groupeLO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FindAnimeMainPage extends AbstractPage{

    @FindBy (css = ".user-avatar")
    private WebElement user;

    @FindBy (id = "q-selectized")
    private WebElement search;

    @FindBy (css = ".selectize-dropdown-content > div:nth-child(1)")
    private WebElement firstSearchResult;

    public FindAnimeMainPage(WebDriver driver) {
        super(driver);
    }

    public void searchAnime(String text) {
        this.search.sendKeys(text);
        this.firstSearchResult.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("ia__cusima"));
    }
}