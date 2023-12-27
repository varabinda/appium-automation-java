package in.codeforz.web.pom.action;

import in.codeforz.utils.WUtil;
import in.codeforz.web.pom.locator.IMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Menu implements IMenu {
    WebDriver webDriver;

    public Menu(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void isMenuDisplayed() {
        WebElement myInspectionsButton = WUtil.waitFor(webDriver, new By.ByXPath(MY_INSPECTIONS_BUTTON_XPATH));
        Assert.assertNotNull(myInspectionsButton, "Error, my inspections menu button not found");

        //TODO: Add the button text validation, need the id for 3rd child div
    }

    public void clickOnMyInspections() {
        By locator = new By.ByXPath(MY_INSPECTIONS_BUTTON_XPATH);
        WebElement myInspectionsButton = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(myInspectionsButton, "Error, my inspections menu button not found");

        Assert.assertTrue(WUtil.isElementClickable(webDriver, locator), "Error, my inspections " +
                "menu button not clickable");
        myInspectionsButton.click();
    }

    public void clickOnProject() {
        By locator = new By.ByXPath(PROJECTS_BUTTON_XPATH);
        WebElement projectsButton = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(projectsButton, "Error, projects menu button not found");

        Assert.assertTrue(WUtil.isElementClickable(webDriver, locator), "Error, projects " +
                "menu button not clickable");
        projectsButton.click();
    }

    public void clickOnLogout() {
        By locator = new By.ByXPath(LOGOUT_BUTTON_XPATH);
        WebElement logoutButton = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(logoutButton, "Error, logout menu button not found");

        // TODO: scroll the header menu and click logout
        // add data-testid to the menu button container
        Assert.assertTrue(WUtil.isElementClickable(webDriver, locator), "Error, logout " +
                "menu button not clickable");
        // TODO: Scroll menu and click on logout
        //logoutButton.click();
    }
}
