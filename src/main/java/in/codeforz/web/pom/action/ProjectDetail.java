package in.codeforz.web.pom.action;

import in.codeforz.utils.WUtil;
import in.codeforz.web.pom.locator.IProjectDetail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ProjectDetail implements IProjectDetail {
    private WebDriver webDriver;
    public ProjectDetail(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void isOnProjectDetail() {
        By locator = new By.ByXPath(PROJECT_SUBTITLE_XPATH);
        WebElement subtitle = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(subtitle, "Error, project details subtitle not found");
    }

    public void clickOnBack() {
        By locator = new By.ByXPath(HEADER_BACK_BUTTON_XPATH);
        WebElement back = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(back, "Error, header back navigation button not found");
        back.click();

    }
}
