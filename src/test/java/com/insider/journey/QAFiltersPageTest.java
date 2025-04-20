package com.insider.journey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        List<WebElement> jobCards = driver.findElements(By.xpath("//div[contains(@class,'position-list-item-wrapper')]"));
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
