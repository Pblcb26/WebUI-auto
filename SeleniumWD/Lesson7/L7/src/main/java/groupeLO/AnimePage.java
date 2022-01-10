package groupeLO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AnimePage extends AbstractPage{

    @FindBy (css = ".chapter-link.btn")
    private WebElement startLooking;

    public AnimePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLooking() {
        this.startLooking.click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("ia__cusima/series1"));
    }
}