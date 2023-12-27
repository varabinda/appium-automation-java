package in.codeforz.web.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TestData {
    final String dataFilePath = "src/test/testData/";
    private JsonObject jsonObject;

    Logger LOGGER = LogManager.getLogger();

    public TestData(String filename) {
        jsonObject = initTestData(filename);
    }

    private JsonObject initTestData(String filename) {
        String filePath = dataFilePath + File.separator + filename;

        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject()) {
                return jsonElement.getAsJsonObject();
            } else {
                LOGGER.error("The root of the JSON file is not an object.");
            }
        } catch (IOException e) {
            LOGGER.error("Error reading the JSON file: " + e.getMessage());
        }
        return null;
    }

    public String getValueByKey(String key) {
        if (jsonObject != null && jsonObject.has(key)) {
            return jsonObject.get(key).getAsString();
        } else {
            LOGGER.error("Key '{}' not found.", key);
            return null;
        }
    }

    public String getValueByKeyPath(String keyPath) {
        if (jsonObject != null) {
            JsonObject currentObject = jsonObject;
            String [] keys = keyPath.split("\\.");

            for (String key : keys) {
                if (currentObject.has(key)) {
                    JsonElement jsonElement = currentObject.get(key);
                    if (jsonElement.isJsonObject()) {
                        currentObject = jsonElement.getAsJsonObject();
                    } else {
                        return jsonElement.getAsString();
                    }
                } else {
                    LOGGER.error("Key path '{}' not found", keyPath);
                    return null;
                }
            }
        }

        return null;
    }
}
