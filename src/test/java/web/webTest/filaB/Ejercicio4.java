package web.webTest.filaB;

import api.config.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import web.webTest.TestBaseTodoLy;

public class Ejercicio4 extends TestBaseTodoLy {
    String originalPassword = "987654321";
    String newPassword = "123456789";

    @Test
    public void verifyChangePassword() throws InterruptedException {
        loginWithCredentials(Configuration.user, originalPassword);
        changePassword(originalPassword, newPassword);
        logout();
        loginWithCredentials(Configuration.user, newPassword);
        Assertions.assertTrue(menuSection.settingButton.isControlDisplayed(), "ERROR: No se pudo cambiar la contraseña");
    }

    private void loginWithCredentials(String username, String password) throws InterruptedException {
        mainPage.loginButton.click();
        loginSection.emailTextBox.setText(username);
        loginSection.passwordTextBox.setText(password);
        loginSection.loginButton.click();
        Thread.sleep(5000);
    }

    private void changePassword(String oldPassword, String newPassword) throws InterruptedException {
        menuSection.settingButton.click();
        Thread.sleep(2000);
        settingsSection.oldPwdTxtBox.setText(oldPassword);
        settingsSection.newPwdTxtBox.setText(newPassword);
        settingsSection.confirmChangesButton.click();
        Thread.sleep(2000);
    }

    private void logout() throws InterruptedException {
        menuSection.logoutButton.click();
        Thread.sleep(2000);
    }
}
