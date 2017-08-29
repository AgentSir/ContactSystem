package ua.sputilov.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.sputilov.ContactSystemApplicationTests;
import ua.sputilov.model.Contact;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactServiceTest extends ContactSystemApplicationTests{

    @Autowired
    ContactService contactService;

    @Test
    @Transactional(readOnly = true)
    public void getContactsAsStreamTest() {

        int countContacts = contactService.countContacts();

        Stream<Contact> contacts = contactService.findAllContacts();
        Assert.assertEquals(countContacts, contacts.collect(Collectors.toList()).size());
    }
}
