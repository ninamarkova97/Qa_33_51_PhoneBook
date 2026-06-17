package tests;
import io.qameta.allure.*;
import manager.DataProviderContact;
import manager.DataProviderUser;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Contact Management System")
@Feature("Add Contacts")
@Owner("NS")
public class AddNewContactTests extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void preCondition() {

        logger.info("test data---> email: lolik@gmail.com & password: Lolik123!");
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().setEmail("lolik@gmail.com").withPassword("Lolik123!"));
        logger.info("User logged in successfully");
    }

    @Test( dataProvider = "contactSuccess", dataProviderClass = DataProviderContact.class)
    @Story("User successfully adds a new contact withb all fields")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "Test Case TC-301", url = "https://example.com/aaa/")
    public void addNewContactSuccessAllFields(Contact contact) {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

       // logger.info("Test data --> name: Tony, lastName:Molly, phone: 34343434"+ i+"email: molly" + i +"@gmail.com," +
               // " address: Haifa, description: all fields");

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(10000);
        app.getHelperContact().getScreen("src/test/screenshots/screen- "+i+".png");
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddByName(contact.getName()));
        logger.info("Assert check is add contact by name");
        Assert.assertTrue(app.getHelperContact().isContactAddByPhone(contact.getPhone()));
        logger.info("Assert check is add contact by phone");

    }
    @Test(dataProvider = "contactCSV",dataProviderClass = DataProviderContact.class)
    public void addNewContactSuccessAllFieldsCSV(Contact contact) {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(10000);
        app.getHelperContact().getScreen("build/screenshots/screen -"+i+".png");
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddByPhone(contact.getPhone()));
    }


    @Test(groups ={"smoke", "regress","retest"})
    public void addNewContactSuccessRequiredFields() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Contact contact = Contact.builder()
                .name("TonyReq"+i)
                .lastName("Molly")
                .phone("34343434" + i)
                .email("molly" + i + "@gmail.com")
                .address("Haifa")
                .build();

        logger.info("Test data --> name: Tony, lastName:Molly, phone: 34343434"+ i+"email: molly" + i +"@gmail.com," +
                " address: Haifa");
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(10000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddByName(contact.getName()));
        logger.info("Assert check is add contact by name");
        Assert.assertTrue(app.getHelperContact().isContactAddByPhone(contact.getPhone()));
        logger.info("Assert check is add contact by phone");


    }
    @Test
    public void addNewContactEmptyName() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Contact contact = Contact.builder()
                .name("")
                .lastName("Molly")
                .phone("343434342323")
                .email("molly@gmail.com")
                .address("Haifa")
                .description("empty name")
                .build();
        logger.info("Test data --> name: , lastName:Molly, phone: 343434342323,email: molly@gmail.com," +
                " address: Haifa, description: empty name");
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(10000);
        app.getHelperContact().saveContact();

        // Assert.assertTrue(app.getHelperContact().isAddLinkActive());
        Assert.assertTrue(app.getHelperContact().isAddContactPageStillDisplayed());
        logger.info("Assert check Add button is active");
    }

    @Test
    public void addNewContactEmptyLastName() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("")
                .phone("343434342323")
                .email("molly@gmail.com")
                .address("Haifa")
                .description("empty last name")
                .build();

        logger.info("Test data --> name: Tony , lastName:, phone: 343434342323,email: molly@gmail.com," +
                " address: Haifa, description: empty last name");

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(10000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddLinkActive());
        logger.info("Assert check Add button is active");
    }

    @Test
    public void addNewContactWrongEmail() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("343434342323")
                .email("mollygmail.com")
                .address("Haifa")
                .description("wrong email")
                .build();
        logger.info("Test data --> name: Tony, lastName:Molly, phone: 343434342323, email: mollygmail.com," +
                " address: Haifa, description: wrong email");
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(10000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddLinkActive());
        logger.info("Assert check Add button is active");
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Email not valid"));
        logger.info("Assert check is alert present with error test 'Email not valid' ");

    }

    @Test(dataProvider = "contactWrongPhone", dataProviderClass = DataProviderContact.class)
    public void addNewContactWrongPhone(Contact contact) {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        logger.info("Test data --> name: Tony, lastName:Molly, phone: , email: molly@gmail.com," +
                " address: Haifa, description: empty phone");
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(10000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Phone not valid"));
        logger.info("Assert check is alert present with error test 'Phone not valid' ");
        Assert.assertTrue(app.getHelperContact().isAddContactPageStillDisplayed());
        logger.info("Assert check Add button is active");

    }

    @Test
    public void addNewContactEmptyAddress() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;

        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("343434342323")
                .email("molly@gmail.com")
                .address("")
                .description("empty address")
                .build();

        logger.info("Test data --> name: Tony, lastName:Molly, phone: 343434342323, email: molly@gmail.com," +
                " address: , description: empty address");


        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(10000);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddLinkActive());
        logger.info("Assert check Add button is active");
    }

}
