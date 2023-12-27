package in.codeforz.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WUtil {
    private static final long TIMEOUT_DURATION = 60L;
    private static final long POLLING_DURATION = 5L;

    public static WebElement waitFor(WebDriver webDriver, By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(TIMEOUT_DURATION))
                .pollingEvery(Duration.ofSeconds(POLLING_DURATION))
                .ignoring(NoSuchElementException.class);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean isElementVisible(WebDriver webDriver, WebElement webElement) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver).withTimeout(Duration.ofSeconds(TIMEOUT_DURATION / 4))
                .pollingEvery(Duration.ofSeconds(POLLING_DURATION / 2))
                .ignoring(NoSuchElementException.class);

        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (TimeoutException e) {
            return false;
        }

        return true;
    }

    public static boolean isElementClickable(WebDriver driver, By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(TIMEOUT_DURATION))
                .pollingEvery(Duration.ofMillis(POLLING_DURATION))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element != null;
    }

    public static boolean isElementClickable(WebDriver driver, WebElement webElement) {
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(TIMEOUT_DURATION))
                .pollingEvery(Duration.ofMillis(POLLING_DURATION))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return element != null;
    }

    public static void scroll(WebDriver webDriver, String direction) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        long windowHeight = (Long) js.executeScript("return window.innerHeight");

        int scrollStep = (int) (windowHeight * 0.8);

        if (direction.equalsIgnoreCase("up")) {
            js.executeScript("window.scrollBy(0, -{});", scrollStep);
        } else if (direction.equalsIgnoreCase("down")) {
            js.executeScript("window.scrollBy(0, {});", scrollStep);
        } else {
            throw new IllegalArgumentException("Invalid scroll direction, use up/down");
        }
    }

}
