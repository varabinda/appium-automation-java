package in.codeforz.mobile.pom;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.testng.Assert;

public class CreateNewProject extends POMBase {

    @iOSXCUITFindBy(accessibility = "TitleCreateProjectModal")
    MobileElement titleCreateProjectModal;
    @iOSXCUITFindBy(accessibility = "SubtitleCreateProjectModal")
    MobileElement subtitleCreateProjectModal;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"ProjectNameInput\"`]")
    MobileElement projectNameInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypePicker[`name == \"OfficeInput\"`]/XCUIElementTypePickerWheel")
    MobileElement officeSelection;
    // **/XCUIElementTypePickerWheel[`value == "Atlanta"`]
    // **/XCUIElementTypePickerWheel[`value == "dep 1 dep 1"`]
    // **/XCUIElementTypePickerWheel[`value == "Andres Construction Services"`]
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypePickerWheel[`value == \"a b\"`]")
    MobileElement supervisorSelection;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypePicker[`name == \"ClientInput\"`]/XCUIElementTypePickerWheel")
    MobileElement clientSelection;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"RepresentativeNameInput\"`]")
    MobileElement representativeNameInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"AddressInput\"`]")
    MobileElement addressInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"CityInput\"`]")
    MobileElement cityInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"StateInput\"`]")
    MobileElement stateInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"ZipCodeInput\"`]")
    MobileElement zipCodeInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"FieldReportNumberPrefixInput\"`]")
    MobileElement fieldReportNumberPrefixInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"ConsultantsProjectNumberInput\"`]")
    MobileElement consultantsProjectNumberInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"StartingVisitInput\"`]")
    MobileElement startingVisitInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"DisputesEmailInput\"`]")
    MobileElement disputesEmailInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"BuildingOptionsInput\"`]")
    MobileElement buildingOptionsInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"FloorOptionsInput\"`]")
    MobileElement floorOptionsInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"PlanGridURLInput\"`]")
    MobileElement planGridURLInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"NJProjectNumberInput\"`]")
    MobileElement njProjectNumberInput;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"ContainContractCompletationDateInput\"`][2]")
    MobileElement containContractCompletationDateInput;

    // TODO: fix issue, dynamic label
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"Mon Dec 18 2023\"`][3]")
    MobileElement contractCompletionDate;
    @iOSXCUITFindBy(accessibility = "NotesForDistributionLabel")
    MobileElement notesForDistributionLabel;
    @iOSXCUITFindBy(accessibility = "NotesForDistributionAddButton")
    MobileElement notesForDistributionAddButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`label == \"NotesForDistributionAddInputInput\"`]")
    MobileElement notesForDistributionAddInputInput;
    @iOSXCUITFindBy(accessibility = "NotesForDistributionConfirmAddButton")
    MobileElement notesForDistributionConfirmAddButton;
    @iOSXCUITFindBy(accessibility = "NotesForDistributionCancelAddButton")
    MobileElement notesForDistributionCancelAddButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"SaveAddProjectButton\"`][2]")
    MobileElement saveAddProjectButton;
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"CancelAddProjectButton\"`][2]")
    MobileElement cancelAddProjectButton;

    public CreateNewProject(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public void isOnCreateNewProject() {
        MobileElement titleMe = mUtil.waitFor(titleCreateProjectModal);
        Assert.assertNotNull(titleMe, "Error, Create new project title not found");

        MobileElement subtitleMe = mUtil.waitFor(subtitleCreateProjectModal);
        Assert.assertNotNull(subtitleMe, "Error, Create new project subtitle not found");
    }

    public void inputProjectName(String name) {
        MobileElement projectNameMe = mUtil.waitFor(projectNameInput);
        Assert.assertNotNull(projectNameMe, "Error, Project name input not found");
        inputText(projectNameMe, name);
    }

    public void selectOffice(String office) {
        MobileElement officeMe = mUtil.waitFor(officeSelection);
        Assert.assertNotNull(officeMe, "Error, office selection not found");
        officeMe.sendKeys(office);
    }

    public void selectSupervisor(String supervisor) {
        MobileElement superMe = mUtil.waitFor(supervisorSelection);
        Assert.assertNotNull(superMe, "Error, supervisor selection not found");
        superMe.sendKeys(supervisor);
    }

    public void selectClient(String client) {
        MobileElement clientMe = mUtil.waitFor(clientSelection);
        Assert.assertNotNull(clientMe, "Error, client selection not found");
        clientMe.sendKeys(client);
    }

    public void inputRepresentativeName(String name) {
//        MobileElement repNameMe = mUtil.waitFor(representativeNameInput);
        mUtil.scroll(cityInput);
        Assert.assertNotNull(representativeNameInput, "Error, Representation name input not found");
        inputText(representativeNameInput, name);
    }

    public void inputAddress(String address) {
//        MobileElement addressMe = mUtil.waitFor(addressInput);
        mUtil.scroll(cityInput);
        Assert.assertNotNull(addressInput, "Error, Address input not found");
        inputText(addressInput, address);
    }

    public void inputCity(String city) {
//        MobileElement cityMe = mUtil.waitFor(cityInput);
        mUtil.scroll(cityInput);
        Assert.assertNotNull(cityInput, "Error, City input not found");
        inputText(cityInput, city);
    }

    public void inputState(String state) {
//        MobileElement stateMe = mUtil.waitFor(stateInput);
        mUtil.scroll(stateInput);
        Assert.assertNotNull(stateInput, "Error, State input not found");
        inputText(stateInput, state);
    }

    public void inputZipcode(String zipcode) {
//        MobileElement zipcodeMe = mUtil.waitFor(zipCodeInput);
        mUtil.scroll(zipCodeInput);
        Assert.assertNotNull(zipCodeInput, "Error, Zipcode input not found");
        inputText(zipCodeInput, zipcode);
    }

    public void inputFieldReportNumberPrefix(String prefix) {
//        MobileElement prefixMe = mUtil.waitFor(fieldReportNumberPrefixInput);
        mUtil.scroll(fieldReportNumberPrefixInput);
        Assert.assertNotNull(fieldReportNumberPrefixInput, "Error, field Report Number Prefix input not found");
        inputText(fieldReportNumberPrefixInput, prefix);
    }

    public void inputConsultantProjectNumber(String number) {
//        MobileElement numberMe = mUtil.waitFor(consultantsProjectNumberInput);
        mUtil.scroll(consultantsProjectNumberInput);
        Assert.assertNotNull(consultantsProjectNumberInput, "Error, fconsultant project number input not found");
        inputText(consultantsProjectNumberInput, number);
    }

    public void inputStartingVisitNumber(String number) {
        mUtil.scroll(startingVisitInput);
        Assert.assertNotNull(startingVisitInput, "Error, starting vist number input not found");
        inputText(startingVisitInput, number);
    }

    public void inputDisputesEmail(String email) {
        mUtil.scroll(disputesEmailInput);
        Assert.assertNotNull(disputesEmailInput, "Error, dispute email input not found");
        inputText(disputesEmailInput, email);
    }

    public void inputBuildingOptions(String building) {
        mUtil.scroll(buildingOptionsInput);
        Assert.assertNotNull(buildingOptionsInput, "Error, building options input not found");
        inputText(buildingOptionsInput, building);
    }

    public void inputFloorOptions(String floor) {
        mUtil.scroll(floorOptionsInput);
        Assert.assertNotNull(floorOptionsInput, "Error, flor options input not found");
        inputText(floorOptionsInput, floor);
    }

    public void inputPlanGridUrl(String url) {
        mUtil.scroll(planGridURLInput);
        Assert.assertNotNull(planGridURLInput, "Error, plangrid url input not found");
        inputText(planGridURLInput, url);
    }

    public void inputNJProjectNumber(String number) {
        mUtil.scroll(njProjectNumberInput);
        Assert.assertNotNull(njProjectNumberInput, "Error, NJ project number input not found");
        inputText(njProjectNumberInput, number);
    }

    public void inputNotesForDistribution(String email) {
        mUtil.scroll(notesForDistributionLabel);
        Assert.assertNotNull(notesForDistributionLabel, "Error, Notes for Distribution label not found");

//        MobileElement addButtonMe = mUtil.waitFor(notesForDistributionAddButton);
//        mUtil.scrollDownToElement(notesForDistributionAddButton);
        Assert.assertNotNull(notesForDistributionAddButton, "Error, Notes for distribution add button not found");
        notesForDistributionAddButton.click();

//        MobileElement distInputMe = mUtil.waitFor(notesForDistributionAddInputInput);
        Assert.assertNotNull(notesForDistributionAddInputInput, "Error, Notes for distribution input not found");
        inputText(notesForDistributionAddInputInput, email);

//        MobileElement confirmMe = mUtil.waitFor(notesForDistributionConfirmAddButton);
        mUtil.scroll(notesForDistributionConfirmAddButton);
        Assert.assertNotNull(notesForDistributionConfirmAddButton, "Error, Notes for distribution confirm add button not found");
        notesForDistributionConfirmAddButton.click();
    }

    public void saveProject() {
        mUtil.scroll(saveAddProjectButton);
        Assert.assertNotNull(saveAddProjectButton, "Error, Save add project buttom not found");
        saveAddProjectButton.click();
    }

    public void cancelProject() {
        mUtil.scroll(cancelAddProjectButton);
        Assert.assertNotNull(cancelAddProjectButton, "Error, Cancel add project buttom not found");
        cancelAddProjectButton.click();
    }

    private void inputText(MobileElement element, String input) {
        element.clear();
        element.sendKeys(input);
        driver.hideKeyboard();
    }
}
