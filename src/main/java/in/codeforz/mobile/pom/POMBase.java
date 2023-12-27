package in.codeforz.mobile.pom;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import in.codeforz.mobile.config.Config;
import in.codeforz.mobile.config.DevicePlatform;
import in.codeforz.utils.MUtil;

public class POMBase {
    protected AppiumDriver driver;
    protected MUtil mUtil;
    protected int waitTimeInSec = 5;
    protected int delayScaler = 10;
    public static int delayInMs = 1000;
    protected DevicePlatform devicePlatform;
    public static int waitInMs = 20000;
    protected static Logger LOGGER = LogManager.getLogger();

    public POMBase(AppiumDriver driver) {
        this.driver = driver;
        this.mUtil = MUtil.getInstance(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, AppiumFieldDecorator.DEFAULT_WAITING_TIMEOUT), this);

        try {
            devicePlatform = Config.getInstance()
                    .getDevicePlatform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
