package in.codeforz.mobile.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class ReadConfig {
    private Config config;
    private static ReadConfig readConfig;
    Logger LOGGER = LogManager.getLogger();

    public ReadConfig(String testTarget, Config config) {
        JSONObject configJson = null;
        String[] targetSplits = testTarget.split("\\.");

        String configFile = "config-ios.json";

        try {
            configJson = new JSONObject(readConfigFile(configFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.config = config;
        LOGGER.info("Test configuration: " + testTarget);

        // determine the execution platform
        if(targetSplits[0].toLowerCase().compareTo(ExecPlatform.LOCAL.toString().toLowerCase()) == 0)
            config.setExecPlatform(ExecPlatform.LOCAL);
        else if(targetSplits[0].toLowerCase().compareTo(ExecPlatform.CLOUD.toString().toLowerCase()) == 0)
            config.setExecPlatform(ExecPlatform.CLOUD);

        JSONObject execPlatformJson = configJson.getJSONObject(targetSplits[0]);
        JSONObject devicePlatformJson = execPlatformJson.getJSONObject(targetSplits[1]);
        config.setDevicePlatform(targetSplits[1]);

        JSONObject serverJson = devicePlatformJson.getJSONObject("server");
        AppiumServer server = new AppiumServer();

        // set url
        server.setUrl(serverJson.getString("url"));

        // set local device details
        if(config.getExecPlatform() == ExecPlatform.LOCAL) {
            server.setAutomationName(serverJson.getString("automationName"));
            server.setLaunchTimeout(serverJson.getInt("commandTimeout"));
            if (config.getDevicePlatform() == DevicePlatform.IOS) {
                server.setLaunchTimeout(serverJson.getInt("launchTimeout"));
            }
        }

        config.setServer(server);

        // set cloud server details
        if(config.getExecPlatform() == ExecPlatform.CLOUD) {
            BrowserStack browserStack= new BrowserStack();
            browserStack.setUserName(serverJson.getString("userName"));
            browserStack.setAccessKey(serverJson.getString("accessKey"));
            browserStack.setUploadApi(serverJson.getString("uploadApi"));

            config.setBrowserStack(browserStack);
        }

        // set app details for local
        JSONObject appJson = devicePlatformJson.getJSONObject("app");
        Map<String, String> app = new HashMap<>();

        if(config.getDevicePlatform() == DevicePlatform.ANDROID) {
            app.put(Config.ANDROID_APP_PACKAGE_KEY, appJson.getString(Config.ANDROID_APP_PACKAGE_KEY));
            app.put(Config.ANDROID_APP_ACTIVITY_KEY, appJson.getString(Config.ANDROID_APP_ACTIVITY_KEY));
            app.put(Config.ANDROID_APP_WAIT_ACTIVITY_KEY, appJson.getString(Config.ANDROID_APP_WAIT_ACTIVITY_KEY));
            app.put(Config.APP_PATH, appJson.getString(Config.APP_PATH));
        } else if(config.getDevicePlatform() == DevicePlatform.IOS) {
            app.put(Config.APP_PATH, appJson.getString(Config.APP_PATH));
            app.put(Config.IOS_APP_BUNDLE_ID, appJson.getString(Config.IOS_APP_BUNDLE_ID));
        }

        config.setApp(app);

        JSONArray targetArray = devicePlatformJson.getJSONArray("targets");
        JSONObject deviceTargetJson = targetArray.getJSONObject(Integer.valueOf(targetSplits[2]));

        DeviceTarget deviceTarget = new DeviceTarget();
        deviceTarget.setDeviceName(deviceTargetJson.getString("deviceName"));
        deviceTarget.setPlatformVersion(deviceTargetJson.getString("platformVersion"));
//        if(config.getDevicePlatform() == DevicePlatform.IOS)
//            deviceTarget.setUdid(deviceTargetJson.getString("udid"));

        config.setDeviceTarget(deviceTarget);

        // set project details for cloud
        if(config.getExecPlatform() == ExecPlatform.CLOUD) {
            JSONObject projectJson = devicePlatformJson.getJSONObject("project");

            Map<String, String> project = new HashMap<>();
            project.put(Config.PROJECT_AUT_BUILD_KEY, projectJson.getString(Config.PROJECT_AUT_BUILD_KEY));
            project.put(Config.PROJECT_AUT_NAME_KEY, projectJson.getString(Config.PROJECT_AUT_NAME_KEY));
            project.put(Config.PROJECT_TEST_NAME_KEY, projectJson.getString(Config.PROJECT_TEST_NAME_KEY));

            config.setProject(project);
        }
    }

    public Config getConfig() {
        return config;
    }

    private String readConfigFile(String fileName) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();

        URL url = classLoader
                .getResource(fileName);

        File file = new File(url.getFile());

        return new String(Files.readAllBytes(file.toPath()));
    }
}
