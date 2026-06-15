package tests;

import io.qameta.allure.*;
import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("User Auth System")
@Feature("User login")
@Owner("Qa Team")
public class LoginTests extends TestBase {


    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        //If button Sign Out present --->logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finished logout");
        }
    }

    @Test(groups = "smoke")
    @Story("Valid user successfully logs with correct credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void loginSuccess1() {
        User user = new User().setEmail("lolik@mail.ru").withPassword("Lolik123!");
//        user.setEmail("lolik@mail.ru");
//        user.setEmail("Lolik123");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }


    @Test(dataProvider = "loginData",dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password) {
        logger.info("Start test with name 'loginSuccess'");
        // logger.info("Test data---> email: 'margo@gmail.com' & password: 'Mmar123456$'");
        logger.info("Test data ---> email: "+ email + " & password: " + password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();

        //Assert
//      Assert.assertEquals();
//      Assert.assertNotEquals();
//      Assert.assertTrue();
//      Assert.assertFalse();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }




    @Test(dataProvider = "loginModels",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) {
        logger.info("Test data---> " + user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        //Assert
//      Assert.assertEquals();
//      Assert.assertNotEquals();
//      Assert.assertTrue();
//      Assert.assertFalse();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }
    @Test(dataProvider = "loginFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelDPF(User user) {
        logger.info("Test data---> " + user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }



    @Test(groups = "smoke")
    @Story("Login fails when user enters invalid email")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("BUG - 1235")

    public void loginWrongEmail() {
        logger.info("Test data --> email:'lolikgmail.com & password: Lolik123!");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("lolikmail.ru", "Lolik123!");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error test 'Wrong email or password' ");
    }

    @Test
    public void loginWrongPassword() {
        logger.info("Test data --> email:'lolik@gmail.com & password: Lolik123");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("lolik@mail.ru", "Lolik123");
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error test 'Wrong email or password' ");

    }

    @Test
    public void loginUnregisteredUser() {
        logger.info("Test data --> email:'loli_k@mail.ru & password: Lolik123!");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("loli_k@mail.ru", "Lolik123!");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error test 'Wrong email or password' ");

    }

}
