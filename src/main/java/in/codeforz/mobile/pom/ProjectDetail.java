package in.codeforz.mobile.pom;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.testng.Assert;

public class ProjectDetail extends POMBase {

    @iOSXCUITFindBy(accessibility = "HeaderBackNavigationButton")
    MobileElement headerBackNavigationButton;
    @iOSXCUITFindBy(accessibility = "HeaderTitle")
    MobileElement headerTitle;

    public ProjectDetail(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void isOnProjectDetail(String projectName) {
        MobileElement titleMe = mUtil.waitFor(headerTitle);
        Assert.assertNotNull(titleMe, "Error, Not on Project Detail");
        // TODO: fix getText() return in caps
        Assert.assertEquals(titleMe.getText().toLowerCase(), projectName, "Project " + projectName + " not found in title");
    }

    public void clickOnBack() {
        MobileElement backMe = mUtil.waitFor(headerBackNavigationButton);
        Assert.assertNotNull(backMe, "Error, Back button not found");
        backMe.click();
    }
}
