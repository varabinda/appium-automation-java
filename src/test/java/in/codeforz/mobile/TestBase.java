package in.codeforz.mobile;

import in.codeforz.web.config.TestData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import in.codeforz.mobile.config.Config;
import in.codeforz.mobile.config.DevicePlatform;
import in.codeforz.mobile.config.ExecPlatform;
import in.codeforz.mobile.driver.AppDriver;

import java.net.MalformedURLException;

public class TestBase {
    private AppiumDriverLocalService server;
    protected Config config;
    protected DevicePlatform devicePlatform;
    protected ExecPlatform execPlatform;
    protected static Logger LOGGER = LogManager.getLogger();
    protected TestData testData;

    protected AppiumDriver driver;
    AppDriver appDriver;
    String appId;

    @BeforeSuite @Parameters({"target"}) public void setUpAppium(String target) {
        config = Config.getInstance(target);
        devicePlatform = config.getDevicePlatform();
        execPlatform = config.getExecPlatform();

        if (ExecPlatform.LOCAL == execPlatform) {
            AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
            serviceBuilder.usingPort(4723);
            serviceBuilder.withArgument(() -> "--base-path", "/wd/hub/");

            LOGGER.info("Starting Appium server...");
            server = AppiumDriverLocalService.buildService(serviceBuilder);
            server.start();
        }
    }

    @BeforeSuite @Parameters({"testData"}) public void setUpTestData(String testDataFile) {
        testData = new TestData(testDataFile);
        LOGGER.info("Setting up test data from file: " + testDataFile);
    }

    @BeforeClass public void setUp() throws MalformedURLException {
        LOGGER.info("Initializing driver");
        appDriver = new AppDriver();
        driver = appDriver.getDriver();
        appId = Config.getInstance()
                .getAppId();
    }

    @AfterClass public void teardown() {
        LOGGER.info("Closing driver");
        if (driver != null) {
            ((InteractsWithApps) driver).terminateApp(appId);
            driver.quit();
        }

        driver = null;
    }

    @AfterSuite public void teardownAppium() {
        if (ExecPlatform.LOCAL == execPlatform && server != null) {
            LOGGER.info("Stopping Appium server...");
            server.stop();
        }

        server = null;
    }
}
