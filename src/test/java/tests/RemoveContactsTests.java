package tests;

import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactsTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().setEmail("lolik@gmail.com").withPassword("Lolik123!"));
      //  app.getHelperContact().provideContacts(); //if list of contacts <3 --> add 3 contacts
    }

    @Test
    public void removeFirstContact(){
        //Assert size contact list less by one
    }

    @Test
    public void removeAllContacts(){
        //Assert -->"No contacts here" is present"

    }
}
