package in.codeforz.web.pom.action;

import in.codeforz.utils.GUtil;
import in.codeforz.utils.WUtil;
import in.codeforz.web.pom.locator.ICreateNewProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class CreateNewProject implements ICreateNewProject {
    final long DELAY_500_MS = 500L;
    private WebDriver webDriver;
    public CreateNewProject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void isOnCreateNewProject() {
        // TODO: remove the hard delay
        GUtil.sleep(500L);
        By locator = new By.ByXPath(TITLE_CREATE_PROJECT_XPATH);
        WebElement title = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(title, "Error, title create project not found");
        Assert.assertEquals(title.getText(), TITLE_CREATE_PROJECT_TEXT, "Error, title create project text do not " +
                "match");

        locator = new By.ByXPath(SUBTITLE_CREATE_PROJECT_XPATH);
        WebElement subtitle = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(subtitle, "Error, subtitle create project not found");
        Assert.assertEquals(subtitle.getText(), SUBTITLE_CREATE_PROJECT_TEXT, "Error, subtitle create project text do" +
                " not match");
    }

    public void inputProjectName(String name) {
        By locator = new By.ByXPath(PROJECT_NAME_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, name), "Project name textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputRepresentativeName(String name) {
        By locator = new By.ByXPath(REPRESENTATIVE_NAME_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, name), "Representative name textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputAddress(String address) {
        By locator = new By.ByXPath(ADDRESS_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, address), "Address textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputCity(String city) {
        By locator = new By.ByXPath(CITY_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, city), "City textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputState(String state) {
        By locator = new By.ByXPath(STATE_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, state), "State textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputZipCode(String zipCode) {
        By locator = new By.ByXPath(ZIPCODE_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, zipCode), "State textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputFieldReportNumberPrefix(String prefix) {
        By locator = new By.ByXPath(FIELD_REPORT_NO_PREFIX_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, prefix), "Field report number prefix textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputConsultantProjectNumber(String number) {
        By locator = new By.ByXPath(CONSULTANT_PROJECT_NO_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, number), "Consultant project number textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputStartingVisitNumber(String number) {
        By locator = new By.ByXPath(STARTING_VISIT_NO_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, number), "Starting visit number textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputDisputesEmail(String email) {
        By locator = new By.ByXPath(DISPUTES_EMAIL_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, email), "Dispute email textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputBuildingOptions(String options) {
        By locator = new By.ByXPath(BUILDING_OPTIONS_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, options), "Building options textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputFloorOptions(String options) {
        By locator = new By.ByXPath(FLOOR_OPTIONS_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, options), "Floor options textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputPlanGridUrl(String url) {
        By locator = new By.ByXPath(PLAN_GRID_URL_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, url), "Plangrid URL textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputNJProjectNumber(String number) {
        By locator = new By.ByXPath(NJ_PROJECT_NO_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(locator, number), "NJ project number textbox not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void selectOffice(String option) {
        Assert.assertTrue(selectFromDropdown(OFFICE_DROPDOWN_XPATH, option), "Office dropdown not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void selectSupervisor(String supervisor) {
        Assert.assertTrue(selectFromDropdown(SUPERVISOR_DROPDOWN_XPATH, supervisor), "Supervisor dropdown not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void selectClient(String client) {
        Assert.assertTrue(selectFromDropdown(CLIENT_DROPDOWN_XPATH, client), "Client dropdown not found");
        GUtil.sleep(DELAY_500_MS);
    }

    public void inputNotesForDistribution(String email) {
        By addButtonLocator = new By.ByXPath(NOTES_FOR_DISTRIBUTION_ADD_BUTTON_XPATH);
        WebElement addButton = WUtil.waitFor(webDriver, addButtonLocator);
        Assert.assertNotNull(addButton, "Add button not found");

        addButton.click();
        By textboxLocator = new By.ByXPath(NOTES_FOR_DISTRIBUTION_TEXTBOX_XPATH);
        Assert.assertTrue(inputText(textboxLocator, email), "Notes for distribution textbox not found");

        By confirmButtonLocator = new By.ByXPath(NOTES_FOR_DISTRIBUTION_CONFIRM_BUTTON_XPATH);
        WebElement confirmButton = WUtil.waitFor(webDriver, confirmButtonLocator);
        Assert.assertNotNull(confirmButton, "Confirm button not found");
        confirmButton.click();
    }

    public void saveProject() {
        By locator = new By.ByXPath(SAVE_ADD_PROJECT_BUTTON_XPATH);
        WebElement save = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(save, "Save button not found");
        Assert.assertTrue(WUtil.isElementClickable(webDriver, locator), "Save button not clickable");
        save.click();
    }

    private boolean selectFromDropdown(String ddXpath, String option) {
        By locator = new By.ByXPath(ddXpath);
        WebElement dropdown = WUtil.waitFor(webDriver, locator);
        Assert.assertNotNull(dropdown, ddXpath + " not found");

        List<WebElement> options = dropdown.findElements(new By.ByXPath(ddXpath + "/option"));
        Assert.assertFalse(options.isEmpty(), ddXpath + " empty list");

        for(int index = 0; index < options.size(); index++) {
            WebElement optionElement = options.get(index);
            if(option.equals(optionElement.getAttribute("label")) || option.equals(optionElement.getText())) {
                Select select = new Select(dropdown);
                select.selectByIndex(index);
                return true;
            }
        }

        return false;

    }

    private boolean selectFromDropdown(By locator, int index) {
        WebElement dropdown = WUtil.waitFor(webDriver, locator);
        if(dropdown == null)
            return false;

        Select select = new Select(dropdown);
        select.selectByIndex(index);

        return true;
    }

    //TODO - move to utils
    private boolean inputText(By locator, String text) {
        WebElement element = WUtil.waitFor(webDriver, locator);
        if(element == null)
            return false;

        element.clear();
        element.sendKeys(text);
        return true;
    }
}
