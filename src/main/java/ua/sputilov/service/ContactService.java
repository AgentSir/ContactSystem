package ua.sputilov.service;

import ua.sputilov.model.Contact;

import java.util.List;
import java.util.stream.Stream;

public interface ContactService {

    Stream<Contact> findAllContacts();
    Iterable<Contact> findAll();
    boolean createOrUpdateContact(Contact contact);
    Integer countContacts();
}
