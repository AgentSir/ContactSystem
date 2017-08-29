package ua.sputilov.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.sputilov.model.Contact;
import ua.sputilov.repositiry.ContactRepository;
import ua.sputilov.service.ContactService;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ContactServiceImpl implements ContactService {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Stream<Contact> findAllContacts() {
        return contactRepository.findAllAndStream();
    }

    @Override
    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public boolean createOrUpdateContact(Contact contact) {
        try {
            contactRepository.save(contact);
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Integer countContacts() {
        try {
            return contactRepository.countContacts();
        } catch (Exception e) {
            LOG.error("ERROR FINDING DATA: " + e.getMessage(), e);
            return null;
        }
    }
}
