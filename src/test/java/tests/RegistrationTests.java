package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static tests.TestBase.app;

public class RegistrationTests extends TestBase {
    Random random = new Random();
    int i = random.nextInt(1000)+1000;


    @BeforeMethod
    public void preCondition() {
        //If button Sign Out present --->logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finished logout");
        }
    }

    @Test
    public void registrationSuccess(){
        //int z = (int)(System.currentTimeMillis()/1000)%3600;
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        System.out.println(i);
        logger.info("Start test with name 'registrationSuccess'");
        User user = new User().setEmail("mert"+i+"@gmail.com").withPassword("Mert123456!");

        logger.info("Test data --> email:'mert" +i+"@gmail.com & password: Mert123456!");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
        logger.info("Assert check is message 'No contacts here'");

    }


    @Test (description = "Bug report #5648") //enabled = false)
    public void wrongEmailRegistration(){
        logger.info("Test data --> email:'mertgmail.com & password: Mert123456!");
        User user = new User().setEmail("mertgmail.com").withPassword("Mert123456!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
        logger.info("Assert check is alert present with error test 'Wrong email or password' ");
        app.getHelperUser().afterAlert();
    }

    @Test
    public void registrationExistsUser(){
        logger.info("Test data --> email:'margo@gmail.com & password: Mmar123456$");
        User user = new User().setEmail("margo@gmail.com").withPassword("Mmar123456$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
        logger.info("Assert check is alert present with error test 'User already exist' ");
    }


    @Test
    public void wrongPasswordRegistration(){
        logger.info("Test data --> email:'mert"+i+"@gmail.com & password: Mert123456");
        User user = new User().setEmail("mert"+i+"@gmail.com").withPassword("Mert123456");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error test 'Wrong email or password' ");
        app.getHelperUser().afterAlert();
    }


}
