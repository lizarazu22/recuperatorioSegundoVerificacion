package web.webTest.filaA;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import web.webTest.TestBaseTodoLy;
import java.util.Random;

public class Ejercicio3 extends TestBaseTodoLy {
    private Random rand = new Random();
    private String nameProject = "NuevoProyecto_Ignacio";
    private String email = "elnacho" + rand.nextInt(1000) + "@hotmail.com";
    private String password = "123456789";

    @Test
    public void verifyCreateUserAndProjectTest() {
        createUser();
        createProject();
    }

    private void createUser() {
        mainPage.signUpButton.click();
        signUpPage.fullNameTextbox.setText("elnacho");
        signUpPage.emailTextbox.setText(email);
        signUpPage.passwordTextbox.setText(password);
        signUpPage.acceptTermsButton.click();
        signUpPage.signUpButton.click();
        Assertions.assertTrue(centralSection.openSettingsButton.isControlDisplayed(), "ERROR! No se pudo crear el usuario");
    }

    private void createProject() {
        projectSection.addProjectButton.click();
        projectSection.projectNameTextBox.setText(nameProject);
        projectSection.projectNameButton.click();
        Assertions.assertTrue(projectSection.addNameToProjectLabel(nameProject).isControlDisplayed(), "ERROR! No se pudo crear el proyecto");
    }
}
