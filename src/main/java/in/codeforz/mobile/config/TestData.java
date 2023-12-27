package in.codeforz.mobile.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;

public class TestData {
    public static JSONObject testDataObject;

    Logger LOGGER = LogManager.getLogger();

    public TestData() {
    }

    public void setTestData(String testFile) {
        String dataFilePath = "src/test/testData/";

        try {
            FileReader reader = new FileReader(dataFilePath + testFile);
            JSONParser jsonParser = new JSONParser();
            testDataObject = (org.json.simple.JSONObject) jsonParser.parse(reader);
        } catch (Exception ex) {
            LOGGER.error("Failed to set test data from file " + testFile);
            ex.printStackTrace();
        }
        LOGGER.info("Test Data obtained from file " + testFile + " is: " + testDataObject);
    }

    public String getValueFromTestDataFile(String key){
        String value = testDataObject.get(key).toString();
        LOGGER.info("Value from test data file for " + key + " is: " + value);
        return value;
    }
}
