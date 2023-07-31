package by.itacademy.shirochina.anastasiya.ui;

import by.itacademy.shirochina.anastasiya.ui.pages.HomePage;
import by.itacademy.shirochina.anastasiya.ui.steps.HomeStep;
import by.itacademy.shirochina.anastasiya.utils.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseTest {
    ChromeDriver driver;
    HomePage homePage;
    HomeStep homeStep;
    Util util;

    @BeforeEach
    public void warmUp() {
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        homePage = new HomePage(driver, wait);
        homeStep = new HomeStep(homePage);
        util = new Util();
    }

    @AfterEach
    public void tearsDown() {
        driver.quit();
    }
}
