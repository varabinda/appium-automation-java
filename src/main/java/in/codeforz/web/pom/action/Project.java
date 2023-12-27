package in.codeforz.web.pom.action;

import in.codeforz.utils.WUtil;
import in.codeforz.web.pom.locator.IProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class Project implements IProject {
    private WebDriver webDriver;
    public Project(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void isOnProject() {
        By locator = new By.ByXPath(PAGE_SUBTITLE_XPATH);
        WebElement subTitle = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(subTitle, "Error, Subtitle (project) not found");
        // TODO: validate the page title with text, locator missing
    }

    // TODO: Fix the dual header create button
    public void clickOnCreateProject() {
        By locator = new By.ByXPath(HEADER_CREATE_BUTTON_XPATH);
        List<WebElement> buttons = webDriver.findElements(locator);

        for (WebElement button : buttons) {
            if(WUtil.isElementVisible(webDriver, button) && WUtil.isElementClickable(webDriver, button)) {
                button.click();
                return;
            }
        }

        Assert.fail("Error, Create project button not found");
    }

    public void searchProject(String name) {
        clickOnSearch();
        inputSearchProject(name);

        String projectEntryXpath = "//div[@data-testid=\"" + name + "-%-Name\"]";
        By locator = new By.ByXPath(projectEntryXpath);
        WebElement projectEntry = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(projectEntry, "Project \'" + name + "\' not created");
    }

    public void inputSearchProject(String name) {
        By locator = new By.ByXPath(HEADER_SEARCH_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, name), "Search textbox not found");
    }

    // TODO: Fix the dual header search button
    public void clickOnSearch() {
        By locator = new By.ByXPath(HEADER_SEARCH_BUTTON_XPATH);
        List<WebElement> buttons = webDriver.findElements(locator);

        for (WebElement button : buttons) {
            if(WUtil.isElementVisible(webDriver, button) && WUtil.isElementClickable(webDriver, button)) {
                button.click();
                return;
            }
        }

        Assert.fail("Error, Search project button not found");
    }

    public void closeSearchBar() {
        By locator = new By.ByXPath(HEADER_SEARCH_CLOSE_BUTTON_XPATH);
        WebElement searchClose = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(searchClose, "Error, header search close button not found");
        searchClose.click();
    }

    // TODO: Fix the dual header menu button
    public void clickOnMenu() {
        By locator = new By.ByXPath(HEADER_MENU_BUTTON_XPATH);
        List<WebElement> buttons = webDriver.findElements(locator);

        for (WebElement button : buttons) {
            if(button.isDisplayed()) {
                button.click();
                return;
            }
        }

        Assert.fail("Error, Search project button not found");

        Menu menu = new Menu(webDriver);
        menu.isMenuDisplayed();
    }

    private boolean inputText(By locator, String text) {
        WebElement element = WUtil.waitFor(webDriver, locator);
        if(element == null)
            return false;

        element.clear();
        element.sendKeys(text);
        return true;
    }
}
