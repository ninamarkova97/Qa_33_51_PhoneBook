package tests;

import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test
    public void loginSuccess() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginREgistration("margo@gmail.com","Mmar123456$");
        app.getHelperUser().submitLogin();
    }
}
