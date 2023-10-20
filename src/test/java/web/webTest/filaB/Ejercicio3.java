package web.webTest.filaB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import web.webTest.TestBaseTodoist;

import java.util.Random;

public class Ejercicio3 extends TestBaseTodoist {
    private Random rand = new Random();
    String email = "elnacho" + rand.nextInt(10000) + "@hotmail.com";
    String psw = "lizarazuaramayo";

    @Test
    public void verifyCreateUserAndProject() throws InterruptedException {
        registerUser(email, psw, "elnacho");
        createProject("Project_Ignacio");
        Assertions.assertTrue(lateralProjectSection.checkNewProject("Project_Ignacio").isControlDisplayed(), "ERROR! No se pudo crear el proyecto");
    }

    private void registerUser(String email, String password, String name) throws InterruptedException {
        mainTodoistPage.loginButton.click();
        mainTodoistPage.registerButton.click();
        loginPage.emailTextBox.setText(email);
        loginPage.passwordTextBox.setText(password);
        loginPage.loginButton.click();
        loginPage.nameTxtBox.setText(name);
        loginPage.continueButton.click();
        Thread.sleep(2000);
        loginPage.personalButton.click();
        Thread.sleep(5000);
        loginPage.openTodoIstButton.click();
        Thread.sleep(2000);
    }

    private void createProject(String projectName) throws InterruptedException {
        String nameProject = projectName;
        lateralProjectSection.projectSectionLabel.click();
        lateralProjectSection.addProjectButton.click();
        Thread.sleep(2000);
        addProjectPopUp.projectNameTextbox.setText(nameProject);
        Thread.sleep(2000);
        addProjectPopUp.changeColorButton.click();
        addProjectPopUp.selectBlueColor.click();
        Thread.sleep(2000);
        addProjectPopUp.submitNameButton.click();
    }
}
