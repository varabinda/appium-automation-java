package in.codeforz.mobile.project;

import in.codeforz.mobile.TestBase;
import in.codeforz.mobile.pom.*;
import org.testng.annotations.Test;
import us.nspec.mobile.pom.*;
import in.codeforz.utils.GUtil;

public class VerifyProject extends TestBase {

    @Test public void verifyCreateProject() {
        login(testData.getValueByKeyPath("login-details.username"), testData.getValueByKeyPath("login-details" + ".password"));

        MyOfficeInspection inspection = new MyOfficeInspection(driver);
        inspection.isOnMyOfficeInspection();
        inspection.clickOnMenu();

        Menu menu = new Menu(driver);
        menu.clickOnProject();

        Project project = new Project(driver);
        project.isOnProject();
        project.clickOnCreateButton();

        CreateNewProject newProject = new CreateNewProject(driver);
        newProject.isOnCreateNewProject();

        String projectName = String.format("ios-auto-test-%04d", GUtil.randomInt(1, 1000));
        newProject.inputProjectName(projectName);
        newProject.inputRepresentativeName("Sam R. Hendrix");
        newProject.selectOffice("Benton");
        newProject.selectSupervisor("Derek Lewis");
        newProject.selectClient("Casey Development LTD");
        newProject.inputAddress("Mountain View, 1600 Amphitheatre Parkway");
        newProject.inputCity("Mountain View");
        newProject.inputState("CA");
        newProject.inputZipcode("94043");
        newProject.inputFieldReportNumberPrefix("FRP-876352-2A");
        newProject.inputConsultantProjectNumber("CPN-00656.134");
        newProject.inputStartingVisitNumber("2");
        newProject.inputDisputesEmail("mr.america@avengers.heros.us");
        newProject.inputBuildingOptions("Tower 1, Building A-5,");
        newProject.inputFloorOptions("floor 10, floor 5, floor 9");
        newProject.inputPlanGridUrl("http://plangrid.wakanda.com");
        newProject.inputNJProjectNumber("nj-0091");
        newProject.inputNotesForDistribution("thor@avengers.heros.us");
        newProject.saveProject();

        ProjectDetail projectDetail = new ProjectDetail(driver);
        projectDetail.isOnProjectDetail(projectName);
        projectDetail.clickOnBack();

        project = new Project(driver);
        project.searchProject(projectName);
        project.closeSearchBar();

//        logout(project);

    }

    private void login(String username, String password) {
        Login login = new Login(driver);
        login.isOnLoginPage();

        login.inputEmailAddress(username);
        login.clickOnVerifyEmail();

        login.inputPassword(password);
        login.clickOnLogin();
    }

    private void logout(Project project) {
        project.clickOnMenu();

        Menu menu = new Menu(driver);
        menu.clickOnLogout();

        Login login = new Login(driver);
        login.isOnLoginPage();
    }
}
