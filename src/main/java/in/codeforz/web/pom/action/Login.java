package in.codeforz.web.pom.action;

import in.codeforz.utils.WUtil;
import in.codeforz.web.pom.locator.ILogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Login implements ILogin {
    private WebDriver webDriver;
    public Login(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void isOnLoginPage() {
        WebElement emailAddressLabel = WUtil.waitFor(webDriver, new By.ByXPath(EMAIL_ADDRESS_LABEL_XPATH));
        Assert.assertNotNull(emailAddressLabel, "Error, email address label not found");

        String emailAddressLabelText = emailAddressLabel.getText();
        Assert.assertEquals(emailAddressLabelText, EMAIL_ADDRESS_LABEL_TEXT, "Error, email address" +
                " label does not match");
    }

    public void inputEmailAddress(String emailId) {
        WebElement emailIdTB = WUtil.waitFor(webDriver, new By.ByXPath(EMAIL_ADDRESS_TEXTBOX_XPATH));
        Assert.assertNotNull(emailIdTB, "Error, email id textbox not found");

        emailIdTB.sendKeys(emailId);
    }

    public void clickOnVerifyEmail() {
        WebElement verifyEmailButton = WUtil.waitFor(webDriver, new By.ByXPath(VERIFY_EMAIL_BUTTON_XPATH));
        Assert.assertNotNull(verifyEmailButton, "Error, verify email button not found");

        String verifyEmailText = verifyEmailButton.getText();
        Assert.assertEquals(verifyEmailText, VERIFY_EMAIL_BUTTON_TEXT, "Error, verify email text does not " +
                "match");

        Assert.assertTrue(verifyEmailButton.isDisplayed(), "Error, verify email button not displayed");
        verifyEmailButton.click();
    }

    public void verifyCopyRightText() {
        WebElement copyright = WUtil.waitFor(webDriver, new By.ByXPath(COPYRIGHT_TEXT_XPATH));
        Assert.assertNotNull(copyright, "Error, copyright div not found");

        String copyrightText = copyright.getText();
        Assert.assertEquals(copyrightText, COPYRIGHT_TEXT, "Error, copyright text does not match");
    }

    public void waitForPassword() {
        WebElement passwordLabel = WUtil.waitFor(webDriver, new By.ByXPath(PASSWORD_LABEL_XPATH));
        Assert.assertNotNull(passwordLabel, "Error, password label not found");

        String passwordText = passwordLabel.getText();
        Assert.assertEquals(passwordText, PASSWORD_LABEL_TEXT, "Error, password text does not match");
    }

    public void inputPassword(String password) {
        WebElement passwordTB = WUtil.waitFor(webDriver, new By.ByXPath(PASSWORD_TEXTBOX_XPATH));
        Assert.assertNotNull(passwordTB, "Error, passwrod textbox not found");

        passwordTB.sendKeys(password);
    }

    public void clickOnLogin() {
        WebElement loginButton = WUtil.waitFor(webDriver, new By.ByXPath(LOGIN_BUTTON_LABEL_XPATH));
        Assert.assertNotNull(loginButton, "Error, login button not found");

        String loginText = loginButton.getText();
        Assert.assertEquals(loginText, LOGIN_BUTTON_LABEL_TEXT, "Error, login text does not " +
                "match");

        Assert.assertTrue(loginButton.isDisplayed(), "Error, login button not displayed");
        loginButton.click();
    }
}
