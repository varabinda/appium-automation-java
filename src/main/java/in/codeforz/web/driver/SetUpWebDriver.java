package in.codeforz.web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.Map;

public class SetUpWebDriver {
    private WebDriver webDriver;

    public SetUpWebDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                this.webDriver = createChromeDriver();
                break;
            case EDGE:
                this.webDriver = createEdgeDriver();
                break;
            case FIREFOX:
                this.webDriver = createFirefoxDriver();
                break;
            case SAFARI:
                this.webDriver = createSafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    public WebDriver createChromeDriver() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("autofill.profile_enabled", false);

        ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.setExperimentalOption("prefs", prefs);

        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver createEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        return new EdgeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        return new FirefoxDriver(options);
    }

    private static WebDriver createSafariDriver() {
        SafariOptions options = new SafariOptions();
//        options.setUseTechnologyPreview(true);
        WebDriver driver = new SafariDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver getDriver() {
        return webDriver;
    }

    public void quit() {
        webDriver.quit();
    }

    public void close() {
        webDriver.close();
    }
}
