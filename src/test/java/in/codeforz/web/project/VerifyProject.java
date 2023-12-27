package in.codeforz.web.project;

import in.codeforz.utils.GUtil;
import in.codeforz.web.TestBase;
import in.codeforz.web.pom.action.*;
import org.testng.annotations.Test;
import us.nspec.web.pom.action.*;

public class VerifyProject extends TestBase {

    @Test
    public void verifyCreateProject() {
        String projectName = String.format("web-auto-test-%04d", GUtil.randomInt(1, 1000));
        login(username, password);

        MyOfficeInspection myOfficeInspectionPage = new MyOfficeInspection(webDriver);
        myOfficeInspectionPage.isOnHome();
        myOfficeInspectionPage.clickOnMenu();

        Menu menu = new Menu(webDriver);
        menu.clickOnProject();

        Project project = new Project(webDriver);
        // TODO check if on project page
        // project.isOnProject();
        project.clickOnCreateProject();

        CreateNewProject newProject = new CreateNewProject(webDriver);
        newProject.isOnCreateNewProject();
        newProject.inputProjectName(projectName);
        newProject.selectSupervisor("Nigel James");
        newProject.selectClient("BE-CI");
        newProject.inputRepresentativeName("Mr. Iron Man");
        newProject.selectOffice("Benton");
        newProject.inputAddress("Mountain View, 1600 Amphitheatre Parkway");
        newProject.inputCity("Mountain View");
        newProject.inputState("CA");
        newProject.inputZipCode("94043");
        newProject.inputStartingVisitNumber("2");
        newProject.inputDisputesEmail("mr.america@avengers.heros.us");
        newProject.inputBuildingOptions("Tower 1, Building A-5,");
        newProject.inputFloorOptions("floor 10, floor 5, floor 9");
        newProject.inputNotesForDistribution("thor@avengers.heros.us");
        newProject.saveProject();

        ProjectDetail projectDetail = new ProjectDetail(webDriver);
        // TODO check if on project detail page
        // projectDetail.isOnProjectDetail();
        projectDetail.clickOnBack();

        project = new Project(webDriver);
        // TODO check if on project page
        project.searchProject(projectName);
        project.closeSearchBar();

        // TODO: fix the logout issue where the logout menu button
        // is not visible, Scroll, make button visible and click
        // logout(project);

        // TODO: remove hard delay
        GUtil.sleep(3000L);
    }

    private void login(String username, String password) {
        Login loginPage = new Login(webDriver);
        loginPage.isOnLoginPage();

        loginPage.inputEmailAddress(username);
        loginPage.clickOnVerifyEmail();

        loginPage.waitForPassword();
        loginPage.inputPassword(password);
        loginPage.clickOnLogin();
    }

    private void logout(Project project) {
        project.clickOnMenu();

        Menu menu = new Menu(webDriver);
        menu.clickOnLogout();

        Login login = new Login(webDriver);
        login.isOnLoginPage();
    }
}
