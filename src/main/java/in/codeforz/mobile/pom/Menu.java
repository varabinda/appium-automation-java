package in.codeforz.mobile.pom;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.testng.Assert;

public class Menu extends POMBase {

    @iOSXCUITFindBy(accessibility = "MyInspectionsMenuButton")
    MobileElement myInspectionsMenuButton;

    @iOSXCUITFindBy(accessibility = "ProjectsMenuButton")
    MobileElement projectsMenuButton;

    @iOSXCUITFindBy(accessibility = "HeaderCreateButton")
    MobileElement headerCreateButton;

    @iOSXCUITFindBy(accessibility = "OfficesMenuButton")
    MobileElement officesMenuButton;

    @iOSXCUITFindBy(accessibility = "EmployeesMenuButton")
    MobileElement employeesMenuButton;

    @iOSXCUITFindBy(accessibility = "ClientsMenuButton")
    MobileElement clientsMenuButton;

    @iOSXCUITFindBy(accessibility = "CollaboratorsMenuButton")
    MobileElement collaboratorsMenuButton;

    @iOSXCUITFindBy(accessibility = "RolesMenuButton")
    MobileElement rolesMenuButton;

    @iOSXCUITFindBy(accessibility = "PlangridMenuButton")
    MobileElement plangridMenuButton;

    @iOSXCUITFindBy(accessibility = "LogoutMenuButton")
    MobileElement logoutMenuButton;

    @iOSXCUITFindBy(accessibility = "FooterTextMenu")
    MobileElement footerTextMenu;

    public Menu(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void isOnMenu() {
        MobileElement myInspectionMe = mUtil.waitFor(myInspectionsMenuButton);
        Assert.assertNotNull(myInspectionMe, "Error, Menu not found");
    }

    public void clickOnProject() {
        MobileElement projectMe = mUtil.waitFor(projectsMenuButton);
        Assert.assertNotNull(projectMe, "Error, Project menu button not found");

        projectMe.click();
    }

    public void clickOnLogout() {
        MobileElement projectMe = mUtil.waitFor(logoutMenuButton);
        Assert.assertNotNull(projectMe, "Error, Logout menu button not found");

        projectMe.click();
    }
}
