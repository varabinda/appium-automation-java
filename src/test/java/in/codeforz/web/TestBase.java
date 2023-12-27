package in.codeforz.web;

import in.codeforz.web.config.TestData;
import in.codeforz.web.driver.BrowserType;
import in.codeforz.web.driver.SetUpWebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestBase {
    protected WebDriver webDriver;
    TestData testData;
    protected String url, username, password;
    BrowserType browserType;
    private final Logger LOGGER = LogManager.getLogger();

    @BeforeClass
    @Parameters({"testData", "browser"})
    public void setupClass(String testData, String browser) {
        Assert.assertNotNull(testData, "Error, test data file not found");
        loadTestData(testData, browser);

        Assert.assertNotNull(url, "Error, URL not found");
        Assert.assertNotNull(username, "Error, username not found");
        Assert.assertNotNull(password, "Error, password not found");

        initWebDriver();
    }

    private void loadTestData(String testDataFile, String browser) {
        testData = new TestData(testDataFile);

        url = testData.getValueByKey("url");
        username = testData.getValueByKeyPath("login-details.username");
        password = testData.getValueByKeyPath("login-details.password");

        if(browser == null)
            browserType = BrowserType.valueOf(testData.getValueByKey("browser").toUpperCase());
        else
            browserType = BrowserType.valueOf(browser.toUpperCase());

        LOGGER.info("Test data loaded");
    }

    public void initWebDriver() {
        webDriver = new SetUpWebDriver(browserType).getDriver();
        webDriver.get(url);
        LOGGER.info("Web driver inited");
        LOGGER.info("Loaded {}", webDriver.getCurrentUrl());
    }

    @AfterClass
    public void teardownClass() {
        webDriver.quit();
        LOGGER.info("Web driver exited");
    }
}
