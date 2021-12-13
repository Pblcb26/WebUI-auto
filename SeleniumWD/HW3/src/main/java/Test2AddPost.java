import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class Test2AddPost {
    public static void main( String[] args ) {
        String host = "https://grouple.co/internal/auth/login";
        String userName = "Kusemos";
        String password = "12TeSt468";

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //Preconditions: authorization
        driver.get(host);
        driver.findElement(By.id("username")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("remember_me_yes")).click();
        driver.findElement(By.cssSelector(".btn-success")).click();

        //add post
        driver.findElement(By.linkText("Посты")).click();
        driver.findElement(By.linkText("Написать пост")).click();
        driver.findElement(By.id("title")).sendKeys("Тестировщики тестируют");
        driver.findElement(By.cssSelector(".note-editable")).sendKeys("Тестировщики учатся тестировать");
        driver.findElement(By.id("publicPost")).click();
        driver.findElement(By.cssSelector(".btn-info")).click();
        driver.quit();
    }
}
