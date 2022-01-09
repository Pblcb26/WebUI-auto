package org.example.groupeLO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage{

    @FindBy (id = "username")
    private WebElement login;

    @FindBy (id = "password")
    private WebElement password;

    @FindBy (id = "remember_me_yes")
    private WebElement rememberMeCheckbox;

    @FindBy (css = ".btn-success")
    private WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginIn(String login, String password) {
        Actions loginIn = new Actions(getDriver());
        loginIn.sendKeys(this.login, login).sendKeys(this.password, password).click(this.rememberMeCheckbox).click(this.submit).build().perform();
    }
}