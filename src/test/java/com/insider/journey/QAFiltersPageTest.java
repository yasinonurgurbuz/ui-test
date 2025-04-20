package com.insider.journey;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.insider.journey.enums.QAFiltersPage.*;
import static com.insider.journey.enums.TimeConstants.DURATION;
import static com.insider.journey.enums.TimeConstants.POLLINTERVAL;
import static com.insider.journey.enums.Urls.USE_INSIDER_CAREERS_QUALITY_ASSURANCE_API_URL;
import static org.awaitility.Awaitility.await;

@Listeners(ScreenshotListener.class)
public class QAFiltersPageTest extends BaseTest {

    @Test
    public void testFilterQAJobsInIstanbul() {
        driver.get(USE_INSIDER_CAREERS_QUALITY_ASSURANCE_API_URL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement seeAllQAJobsBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEE_ALL_QA_JOBS_BUTTON_X_PATH)));
        seeAllQAJobsBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(FILTER_BY_LOCATION_CSS)));

        WebElement locationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOCATION_DROP_DOWN_X_PATH)));
        locationDropdown.click();

        waitForIstanbulOptionToBeClickable(locationDropdown);

        WebElement departmentDropdown = driver.findElement(By.xpath(DEPARTMENT_DROP_DOWN_X_PATH));
        Select departmentSelect = new Select(departmentDropdown);
        departmentSelect.selectByVisibleText("Quality Assurance");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(POSITION_LIST_X_PATH)));

        List<WebElement> jobCards = driver.findElements(By.xpath(JOB_CARDS_X_PATH));

        Assert.assertFalse(jobCards.isEmpty(), "No job listings found for QA in Istanbul!");

        System.out.println("Found " + jobCards.size() + " QA job(s) in Istanbul.");
    }

    @Test(dependsOnMethods = "testFilterQAJobsInIstanbul")
    public void verifyFilteredJobDetails() {
        List<WebElement> jobCards = driver.findElements(By.xpath(JOB_CARD_X_PATH));
        Assert.assertFalse(jobCards.isEmpty(), "No job listings found!");

        for (WebElement card : jobCards) {
            String title = card.findElement(By.cssSelector(".position-title")).getText().trim().toLowerCase();
            String department = card.findElement(By.cssSelector(".position-department")).getText().trim();
            String location = card.findElement(By.cssSelector(".position-location")).getText().trim();

            if (department.equals("Quality Assurance") && location.equals("Istanbul, Turkiye")) {
                Assert.assertTrue(title.contains("quality assurance"),
                        "Job title does not contain 'Quality Assurance': " + title);
            }
        }

        System.out.println("All job listings are verified successfully!");
    }

    @Test(dependsOnMethods = {"testFilterQAJobsInIstanbul", "verifyFilteredJobDetails"})
    public void verifyRedirectToLeverPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement firstJobCard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(FIRST_JOB_CARD_X_PATH)));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", firstJobCard);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement viewRoleBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(VIEW_ROLES_X_PATH)));

        new Actions(driver).moveToElement(viewRoleBtn).perform();

        viewRoleBtn.click();

        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        wait.until(ExpectedConditions.urlContains("jobs.lever.co"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("jobs.lever.co"),
                "Redirected URL is not a Lever application form page: " + currentUrl);

        System.out.println("Redirected successfully to: " + currentUrl);
    }

    public void waitForIstanbulOptionToBeClickable(WebElement locationDropdown) {
        await()
                .atMost(DURATION, TimeUnit.SECONDS)
                .pollInterval(POLLINTERVAL, TimeUnit.MILLISECONDS)
                .until(() -> {
                    try {
                        locationDropdown.click();
                        WebElement istanbulOption = driver.findElement(
                                By.xpath(ISTANBUL_OPTION_X_PATH)
                        );
                        if (istanbulOption.isDisplayed()) {
                            istanbulOption.click();
                            return true;
                        }
                    } catch (Exception ignored) {
                    }
                    return false;
                });
    }
}
