package in.codeforz.web.login;

import in.codeforz.web.TestBase;
import org.testng.annotations.Test;
import in.codeforz.web.pom.action.MyOfficeInspection;
import in.codeforz.web.pom.action.Login;
import in.codeforz.web.pom.action.Menu;

public class VerifyLogin extends TestBase {

    @Test
    public void testLoginWithValidCredentials() {
        Login loginPage = new Login(webDriver);
        loginPage.isOnLoginPage();

        loginPage.verifyCopyRightText();
        loginPage.inputEmailAddress(username);
        loginPage.clickOnVerifyEmail();

        loginPage.waitForPassword();
        loginPage.inputPassword(password);
        loginPage.clickOnLogin();

        MyOfficeInspection myOfficeInspectionPage = new MyOfficeInspection(webDriver);
        myOfficeInspectionPage.isOnHome();
    }

    @Test(dependsOnMethods = {"testLoginWithValidCredentials"})
    public void verifyLogout() {
        MyOfficeInspection myOfficeInspectionPage = new MyOfficeInspection(webDriver);
        myOfficeInspectionPage.isOnHome();
        myOfficeInspectionPage.clickOnMenu();

        Menu menu = new Menu(webDriver);
        menu.clickOnLogout();

        Login login = new Login(webDriver);
        login.isOnLoginPage();
    }
}
