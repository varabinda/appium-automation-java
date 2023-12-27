package in.codeforz.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextReporter  implements ITestListener, IReporter {
    private Logger LOGGER = LogManager.getLogger();
    private Map<String, String> testPassed = new HashMap();
    private Map<String, String> testFailed = new HashMap();
    private Map<String, String> testSkipped = new HashMap();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String testMethod = iTestResult.getMethod().getMethodName();
        String testClass = getClassName(iTestResult.getTestClass().getName());
        LOGGER.info("Starting test \"{}.{}\"", testClass, testMethod);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String testMethod = iTestResult.getMethod().getMethodName();
        String testClass = getClassName(iTestResult.getTestClass().getName());
        LOGGER.info("Completed test \"{}.{}\", Status: PASS", testClass, testMethod);
        testPassed.put(testClass + "." + testMethod, "PASS");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String testMethod = iTestResult.getMethod().getMethodName();
        String testClass = getClassName(iTestResult.getTestClass().getName());
        LOGGER.info("Completed test \"{}.{}\", Status: FAIL", testClass, testMethod);
        testFailed.put(testClass + "." + testMethod, "FAIL");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String testMethod = iTestResult.getMethod().getMethodName();
        String testClass = getClassName(iTestResult.getTestClass().getName());
        LOGGER.info("Completed test \"{}.{}\", Status: SKIP", testClass, testMethod);
        testSkipped.put(testClass + "." + testMethod, "SKIP");
    }

    private String getClassName(String fullyQualifiedName) {
        String [] tmpStringArray = fullyQualifiedName.split("\\.");
        return tmpStringArray[tmpStringArray.length - 1];
    }

    @Override
    public void generateReport(List<XmlSuite> list, List<ISuite> suites, String s) {
        StringBuilder summary = new StringBuilder();
        int totalTestCount = testFailed.size() + testPassed.size() + testSkipped.size();
        int testCount = 1;

        summary.append("=========================================================================\n");
        summary.append("\tTest Summary: Test count: " + String.valueOf(totalTestCount) + ", Pass: "
                + String.valueOf(testPassed.size()) + ", Fail: " + String.valueOf(testFailed.size())
                + ", Skip: " + String.valueOf(testSkipped.size()) + "\n");
        summary.append("=========================================================================\n");
        for(Map.Entry<String, String> entry : testPassed.entrySet())
            summary.append("\t" + String.valueOf(testCount++) + ". " + entry.getKey() + " :: " + entry.getValue() + "\n");

        for(Map.Entry<String, String> entry : testFailed.entrySet())
            summary.append("\t" + String.valueOf(testCount++) + ". " + entry.getKey() + " :: " + entry.getValue() + "\n");

        for(Map.Entry<String, String> entry : testSkipped.entrySet())
            summary.append("\t" + String.valueOf(testCount++) + ". " + entry.getKey() + " :: " + entry.getValue() + "\n");

        summary.append("=========================================================================\n");

        LOGGER.info("\n" + summary);

        float totalFailed = testFailed.size() + testSkipped.size();
        float stability = 1.0F - totalFailed/totalTestCount;
        LOGGER.info("\nStability: " + String.format("%.02f", stability));
    }

}
