package in.codeforz.web.pom.action;

import in.codeforz.utils.WUtil;
import in.codeforz.web.pom.locator.IHome;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MyOfficeInspection implements IHome {
    WebDriver webDriver;
    public MyOfficeInspection(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void isOnHome() {
        WebElement subTitle = WUtil.waitFor(webDriver, new By.ByXPath(SUBTITLE_TEXT_XPATH));
        Assert.assertNotNull(subTitle, "Error, Subtitle not found");

        String subTitleText = subTitle.getText();
        Assert.assertEquals(subTitleText, SUBTITLE_TEXT, "Error, subtitle text does not match");
    }

    public void clickOnMenu() {
        WebElement menuButton = WUtil.waitFor(webDriver, new By.ByXPath(HEADER_MENU_BUTTON_XPATH));
        Assert.assertNotNull(menuButton, "Error, menu button not found");

        Assert.assertTrue(menuButton.isDisplayed(), "Error, menu button not displayed");
        menuButton.click();

        Menu menu = new Menu(webDriver);
        menu.isMenuDisplayed();
    }
}
