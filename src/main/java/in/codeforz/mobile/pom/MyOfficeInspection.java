package in.codeforz.mobile.pom;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.time.Duration;

public class MyOfficeInspection extends POMBase {
    @iOSXCUITFindBy(accessibility = "HeaderTitle")
    MobileElement headerTitle;
    @iOSXCUITFindBy(accessibility = "HeaderSubtitle")
    MobileElement headerSubtitle;
    @iOSXCUITFindBy(accessibility = "HeaderMenuButton")
    MobileElement headerMenuButton;

    @iOSXCUITFindBy(accessibility = "HeaderSearchButton")
    MobileElement headerSearchButton;

    @iOSXCUITFindBy(accessibility = "HeaderCreateButton")
    MobileElement headerCreateButton;

    public MyOfficeInspection(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void isOnMyOfficeInspection() {
        // TODO: remove custom wait
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(3000))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        MobileElement titleMe = (MobileElement) wait.until(ExpectedConditions.visibilityOf(headerTitle));

//        MobileElement titleMe = mUtil.waitFor(headerTitle);
        Assert.assertNotNull(titleMe, "Error, My office inspection not found");
    }

    public void clickOnMenu() {
        MobileElement menuMe = mUtil.waitFor(headerMenuButton);
        Assert.assertNotNull(menuMe, "Error, Header menu button not found");
        menuMe.click();
    }
}
