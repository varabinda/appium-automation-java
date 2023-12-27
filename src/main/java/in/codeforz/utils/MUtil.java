package in.codeforz.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

import static io.appium.java_client.touch.offset.PointOption.point;

public class MUtil {
    private final AppiumDriver<MobileElement> driver;
    private static MUtil mUtil;

    private Logger LOGGER = LogManager.getLogger();

    private MUtil(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public static MUtil getInstance(AppiumDriver<MobileElement> driver) {
        if (null == mUtil)
            mUtil = new MUtil(driver);
        return mUtil;
    }

    private static long TIMEOUT_DURATION = 60L;
    private static long POLLING_DURATION = 100L;

    public void scroll(MobileElement element) {
        int count = 10;
        while (count > 0 && !element.isDisplayed()) {
            scroll(0, 0, 0, 0);
            count--;
        }
    }

    public void scroll(MobileElement pElement, String locator) {
        Coordinates cords = pElement.getCoordinates();
        Point center = pElement.getCenter();

        int startX = cords.onPage().getX() + center.getX();
        int startY = cords.onPage().getY() + center.getY();

        WebElement tElement = null;
        int count = 10;
        do {
            pElement.click();
            scroll(startX, startY, startX, startX - 40);
            try {
                tElement = ((IOSDriver) driver).findElementByIosClassChain(locator);
            } catch (NoSuchElementException ignored) {

            }
            if(null != tElement && tElement.isDisplayed())
                return;
            count--;
        } while(count > 0);
    }

    public void scroll(int startX, int startY, int endX, int endY) {
        Dimension size = driver.manage()
                .window()
                .getSize();
        if (startX == 0 || startY == 0 || endX == 0 || endY == 0) {
            startX = size.width / 5;
            startY = (int) (size.height * 0.8);
            endY = (int) (size.height * 0.4);
        }

        new TouchAction(driver).press(point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(point(startX, endY))
                .release()
                .perform();

    }

    public MobileElement waitFor(MobileElement element) {
        int retries = 3;
        while(retries > 0) {
            try {
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(TIMEOUT_DURATION))
                        .pollingEvery(Duration.ofMillis(POLLING_DURATION))
                        .ignoring(NoSuchElementException.class);

                return (MobileElement) wait.until(ExpectedConditions.visibilityOf(element));
            } catch (StaleElementReferenceException exp) {
                LOGGER.error("Stale element reference exception");
                retries--;
            }
        }

        return null;
    }

    public MobileElement waitFor(By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT_DURATION))
                .pollingEvery(Duration.ofSeconds(POLLING_DURATION))
                .ignoring(NoSuchElementException.class);

        return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
