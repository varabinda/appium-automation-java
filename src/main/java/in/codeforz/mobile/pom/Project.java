package in.codeforz.mobile.pom;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.testng.Assert;

public class Project extends POMBase {
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label == \"PROJECTS\"`]")
    MobileElement screenTitle;
    @iOSXCUITFindBy(accessibility = "HeaderMenuButton")
    MobileElement headerMenuButton;

    @iOSXCUITFindBy(accessibility = "HeaderSearchInput")
    MobileElement headerSearchInput;

    @iOSXCUITFindBy(accessibility = "HeaderSearchButton")
    MobileElement headerSearchButton;

    @iOSXCUITFindBy(accessibility = "HeaderCreateButton")
    MobileElement headerCreateButton;

    @iOSXCUITFindBy(accessibility = "HeaderSearchCloseButton")
    MobileElement headerSearchCloseButton;

    public Project(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void isOnProject() {
        MobileElement titleMe = mUtil.waitFor(screenTitle);
        Assert.assertNotNull(titleMe, "Error, Project not " + "found");
    }

    public void clickOnCreateButton() {
        MobileElement menuMe = mUtil.waitFor(headerCreateButton);
        Assert.assertNotNull(menuMe, "Error, Header create button not " + "found");

        menuMe.click();
    }

    public void clickOnSearchButton() {
        MobileElement menuMe = mUtil.waitFor(headerSearchButton);
        Assert.assertNotNull(menuMe, "Error, Header search button not " + "found");

        menuMe.click();
    }

    public void inputSearchProject(String name) {
        MobileElement searchInputMe = mUtil.waitFor(headerSearchInput);
        Assert.assertNotNull(searchInputMe, "Error, Header search input not " + "found");
        searchInputMe.sendKeys(name);
        driver.hideKeyboard();
    }

    public void clickOnMenu() {
        MobileElement menuMe = mUtil.waitFor(headerMenuButton);
        Assert.assertNotNull(menuMe, "Error, Header menu button not found");

        menuMe.click();
    }

    public void searchProject(String name) {
        MobileElement searchMe = mUtil.waitFor(headerSearchButton);
        Assert.assertNotNull(searchMe, "Error, Header search button not found");
        searchMe.click();

        headerSearchInput.sendKeys(name);
        By locator = new By.ById(name);
        MobileElement nameMe = mUtil.waitFor(locator);
        Assert.assertNotNull(nameMe, "Error, project" + name + " not created");
    }

    public void closeSearchBar() {
        MobileElement searchMe = mUtil.waitFor(headerSearchCloseButton);
        Assert.assertNotNull(searchMe, "Error, Header search close button not found");
        searchMe.click();
    }
}
