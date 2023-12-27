package in.codeforz.web.pom.locator;

public interface ILogin {
    final String EMAIL_ADDRESS_LABEL_TEXT = "Email Address:";
    final String EMAIL_ADDRESS_LABEL_XPATH = "//div[@data-testid=\"LoginEmailAddressLabel\"]";
    final String EMAIL_ADDRESS_TEXTBOX_XPATH = "//input[@data-testid=\"LoginEmailAddressInput\"]";
    final String VERIFY_EMAIL_BUTTON_XPATH = "//div[@data-testid=\"VerifyEmailButton\"]";
    final String VERIFY_EMAIL_BUTTON_TEXT = "VERIFY EMAIL";
    final String COPYRIGHT_TEXT_XPATH = "//div[@data-testid=\"LoginCopyrightText\"]";
    final String COPYRIGHT_TEXT = "©2021 Building Engineering Consultants, Inc. \n" +
            "All Rights Reserved, Version 4.0.1.20231208.1";
    final String PASSWORD_LABEL_XPATH = "//div[@data-testid=\"LoginPasswordLabel\"]";
    final String PASSWORD_LABEL_TEXT = "Password:";
    final String PASSWORD_TEXTBOX_XPATH = "//input[@data-testid=\"LoginPasswordInput\"]";
    final String FORGET_PASSWORD_BUTTON_XPATH = "//input[@data-testid=\"ForgotPasswrodButton\"]";
    final String FORGET_PASSWORD_BUTTON_TEXT = "Forgot Password?";
    final String LOGIN_BUTTON_LABEL_XPATH = "//div[@data-testid=\"LoginButton\"]";
    final String LOGIN_BUTTON_LABEL_TEXT = "LOGIN";


}
