package web.webTest.filaA;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import web.webTest.TestBaseTodoist;

public class Ejercicio4 extends TestBaseTodoist {

    @Test
    public void verifyCreateTask() throws InterruptedException {
        loginUser("elnacho@hotmail.com", "lizarazuaramayo");
        createTask("Task_Ignacio");
        Assertions.assertTrue(navBarSection.taskExists("Task_Ignacio"), "ERROR! No se pudo crear la tarea");
    }

    private void loginUser(String email, String password) throws InterruptedException {
        mainTodoistPage.loginButton.click();
        loginPage.emailTextBox.setText(email);
        loginPage.passwordTextBox.setText(password);
        loginPage.loginButton.click();
    }

    private void createTask(String taskName) throws InterruptedException {
        navBarSection.addTask.click();
        Thread.sleep(2000);
        navBarSection.taskName.setText(taskName);
        Thread.sleep(2000);
        navBarSection.buttonOk.click();
        Thread.sleep(1000);
        navBarSection.buttonCancel.click();
    }
}
