package com.insider.journey;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.insider.journey.enums.Urls.USE_INSIDER_API_URL;

public class InsiderHomePageTest extends BaseTest {

    @Test
    public void testHomePageIsOpened() {
        driver.get(USE_INSIDER_API_URL);

        String expectedUrl = "https://useinsider.com/";
        String actualUrl = driver.getCurrentUrl();

        String title = driver.getTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Insider"), "Title does not contain 'Insider'");

        Assert.assertNotNull(actualUrl);
        Assert.assertTrue(actualUrl.startsWith(expectedUrl), "URL is not as expected");
    }
}
