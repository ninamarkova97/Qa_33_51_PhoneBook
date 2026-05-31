package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("34343434246")
                .email("molly@gmail.com")
                .address("Haifa")
                .description("all fields")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("6555652654")
                .email("mony@gmail.com")
                .address("Haifa")
                .build()});


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("343")
                .email("molly@gmail.com")
                .address("Haifa")
                .description("all fields")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .phone("343434312345678909876")
                .email("john@gmail.com")
                .address("Haifa")
                .description("Friend")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .phone("wwwwwwwwwwwwwwwwwww")
                .email("john@gmail.com")
                .address("Haifa")
                .description("Friend")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .phone("")
                .email("john@gmail.com")
                .address("Haifa")
                .description("Friend")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .phone("123-123-789-25")
                .email("john@gmail.com")
                .address("Haifa")
                .description("Friend")
                .build()});
        return list.iterator();
    }
}
