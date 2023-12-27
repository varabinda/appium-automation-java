package in.codeforz.mobile.driver;

import in.codeforz.mobile.config.ExecPlatform;
import in.codeforz.mobile.config.Config;
import in.codeforz.mobile.config.DevicePlatform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AppDriver {
    private AppiumDriver<MobileElement> driver;
    private Config config;
    private URL url;
    private DesiredCapabilities desiredCapabilities;
    Logger LOGGER = LogManager.getLogger();

    public AppDriver() throws MalformedURLException {
        LOGGER.info("Setting capabilities");
        this.config = Config.getInstance();

        url = new URL(config.getServer().getUrl());

        desiredCapabilities = new DesiredCapabilities();

        initDriver();
    }

    public void initDriver() {
        if(config.getExecPlatform() == ExecPlatform.CLOUD) {
            setCapabilitiesForCloud();

            if (config.getDevicePlatform() == DevicePlatform.ANDROID) {
                driver = new AndroidDriver<>(url, desiredCapabilities);
            } else if (config.getDevicePlatform() == DevicePlatform.IOS) {
                driver = new IOSDriver<>(url, desiredCapabilities);
            }

        } else if(config.getExecPlatform() == ExecPlatform.LOCAL) {
            setCapabilitiesForLocal();

            if (config.getDevicePlatform() == DevicePlatform.ANDROID) {
                setCapabilitiesForLocalAndroid();
                driver = new AndroidDriver<>(url, desiredCapabilities);
            } else if (config.getDevicePlatform() == DevicePlatform.IOS) {
                setCapabilitiesForLocalIos();
                driver = new IOSDriver<>(url, desiredCapabilities);
            }
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public DesiredCapabilities getDesiredCapabilities() {
        return desiredCapabilities;
    }

    private void setCapabilitiesForCloud() {
        LOGGER.info("Running tests on " + ExecPlatform.CLOUD.toString());
        desiredCapabilities.setCapability("browserstack.user", config.getBrowserStack().getUserName());
        desiredCapabilities.setCapability("browserstack.key", config.getBrowserStack().getAccessKey());

        desiredCapabilities.setCapability("device", config.getDeviceTarget().getDeviceName());
        desiredCapabilities.setCapability("os_version", config.getDeviceTarget().getPlatformVersion());

        desiredCapabilities.setCapability("project", config.getProject().get(Config.PROJECT_AUT_NAME_KEY));
        desiredCapabilities.setCapability("build", config.getProject().get(Config.PROJECT_AUT_BUILD_KEY));
        desiredCapabilities.setCapability("name", config.getProject().get(Config.PROJECT_TEST_NAME_KEY));

//        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        desiredCapabilities.setCapability("autoGrantPermissions", true);

        desiredCapabilities.setCapability("app", getBrowserStackAppTag(config.getApp().get(Config.APP_PATH)));

    }

    private void setCapabilitiesForLocal() {
        LOGGER.info("Running tests on " + ExecPlatform.LOCAL.toString());
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, config.getDeviceTarget().getDeviceName());
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, config.getDevicePlatform().toString());
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, config.getDeviceTarget().getPlatformVersion());
        desiredCapabilities.setCapability(MobileCapabilityType.APP, config.getApp().get(Config.APP_PATH));
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, config.getServer().getAutomationName());
        desiredCapabilities.setCapability("fullReset", true);
    }

    private void setCapabilitiesForLocalAndroid() {
        desiredCapabilities.setCapability(Config.ANDROID_APP_PACKAGE_KEY, config.getApp().get(Config.ANDROID_APP_PACKAGE_KEY));
        desiredCapabilities.setCapability(Config.ANDROID_APP_WAIT_ACTIVITY_KEY, config.getApp().get(Config.ANDROID_APP_WAIT_ACTIVITY_KEY));
        desiredCapabilities.setCapability(Config.ANDROID_APP_ACTIVITY_KEY, config.getApp().get(Config.ANDROID_APP_ACTIVITY_KEY));
        desiredCapabilities.setCapability("autoGrantPermissions", true);
    }

    private void setCapabilitiesForLocalIos() {
        desiredCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, config.getApp().get(Config.IOS_APP_BUNDLE_ID));
//        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        desiredCapabilities.setCapability("udid", "auto");
        desiredCapabilities.setCapability("xcodeSigningId", "iPhone Developer");
        desiredCapabilities.setCapability("xcodeOrgId","GMSGY63W5S");
    }

    private String getBrowserStackAppTag(String appPath) {
        appPath = System.getProperty("user.dir") + appPath.substring(1);
        LOGGER.info("Uploading " + appPath);
        OkHttpClient client = createAuthenticatedClient(config.getBrowserStack().getUserName(), config.getBrowserStack().getAccessKey());
        Response response = null;

        int index = appPath.split("/").length - 1;
        String appFilename = appPath.split("/")[index];

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file",appFilename,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(appPath)))
                .build();
        Request request = new Request.Builder()
//                .url("https://api-cloud.browserstack.com/app-automate/upload")
                .url(Config.getInstance().getBrowserStack().getUploadApi())
                .method("POST", body)
                .build();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(response != null);
        assert(response.code() == 200);
        String responseString = null;

        try {
            responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(responseString != null);
        LOGGER.info("BS App tag: " + responseString.split("\"")[3]);
        return responseString.split("\"")[3];
    }

    private OkHttpClient createAuthenticatedClient(final String username,
                                                          final String password) {
        // build client with authentication information.
        OkHttpClient httpClient = new OkHttpClient.Builder().authenticator(new Authenticator() {
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, password);
                if (responseCount(response) >= 3) {
                    return null;
                }
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        }).build();
        return httpClient;
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
