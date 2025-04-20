package com.insider.journey;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

import static com.insider.journey.enums.CareerPage.*;
import static com.insider.journey.enums.Urls.USE_INSIDER_API_URL;

public class CareersPageTest extends BaseTest {

    @Test
    public void testCareersPageBlocks() {
        driver.get(USE_INSIDER_API_URL);

        WebElement companyMenu = driver.findElement(By.xpath(COMPANY_MENU_X_PATH));
        Actions actions = new Actions(driver);
        actions.moveToElement(companyMenu).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement careersButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CAREERS_BUTTON_X_PATH)));

        careersButton.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 10000)");

        WebElement locationsBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOCATIONS_BLOCK_X_PATH)));
        WebElement seeAllTeamsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ALL_TEAMS_BUTTON_X_PATH)));
        WebElement lifeAtInsiderBlock = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(LIFE_AT_INSIDER_X_PATH))
        );

        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("careers"), "Careers page did not open");
        Assert.assertTrue(locationsBlock.isDisplayed(), "Locations block not visible");
        Assert.assertTrue(seeAllTeamsButton.isDisplayed(), "See all teams button not visible");
        Assert.assertTrue(lifeAtInsiderBlock.isDisplayed(), "Life at Insider block not visible");
    }
}
