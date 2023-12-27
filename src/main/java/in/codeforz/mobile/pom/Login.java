package in.codeforz.mobile.pom;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.testng.Assert;

public class Login extends POMBase {
    @iOSXCUITFindBy(accessibility = "LoginEmailAddressLabel")
    MobileElement loginEmailAddressLabel;

    @iOSXCUITFindBy(accessibility = "LoginEmailAddressInput")
    MobileElement loginEmailAddressInput;

    @iOSXCUITFindBy(accessibility = "VerifyEmailButton")
    MobileElement verifyEmailButton;

    @iOSXCUITFindBy(accessibility = "LoginPasswordLabel")
    MobileElement loginPasswordLabel;

    @iOSXCUITFindBy(accessibility = "LoginPasswordInput")
    MobileElement loginPasswordInput;

    @iOSXCUITFindBy(accessibility = "ForgotPasswrodButton")
    MobileElement forgotPasswrodButton;

    @iOSXCUITFindBy(accessibility = "LoginButton")
    MobileElement loginButton;

    @iOSXCUITFindBy(accessibility = "LoginCopyrightText")
    MobileElement loginCopyrightText;


    public Login(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void isOnLoginPage() {
        Assert.assertNotNull(mUtil.waitFor(loginEmailAddressLabel), "Error, not on login screen");
    }

    public void inputEmailAddress(String username) {
        MobileElement emailInput = mUtil.waitFor(loginEmailAddressInput);
        Assert.assertNotNull(emailInput, "Error, login email address input not found");
        emailInput.clear();
        emailInput.sendKeys(username);
        driver.hideKeyboard();
    }

    public void clickOnVerifyEmail() {
        MobileElement verifyButton = mUtil.waitFor(verifyEmailButton);
        Assert.assertNotNull(verifyButton, "Error, verify email button not found");

        verifyButton.click();
    }

    public void verifyCopyRightText() {

    }

    public void waitForPassword() {

    }

    public void inputPassword(String password) {
        MobileElement passwordInput = mUtil.waitFor(loginPasswordInput);
        Assert.assertNotNull(passwordInput, "Error, login password input not found");
        passwordInput.clear();
        passwordInput.sendKeys(password);
        driver.hideKeyboard();
    }

    public void clickOnLogin() {
        MobileElement loginButtonMe = mUtil.waitFor(loginButton);
        Assert.assertNotNull(loginButtonMe, "Error, login button not found");

        loginButtonMe.click();
    }

}

