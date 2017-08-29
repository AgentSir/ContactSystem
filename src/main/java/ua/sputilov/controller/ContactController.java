package ua.sputilov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.sputilov.entity.ContactAsJsonString;
import ua.sputilov.exception.EmptyRequestParamException;
import ua.sputilov.model.Contact;
import ua.sputilov.service.ContactService;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/hello")
public class ContactController {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ContactService contactService;

    @Transactional(readOnly = true)
    @GetMapping(value = "/contacts",
                produces = "application/json")
    public void searchContactsWithNegativeFilterByName(@RequestParam(value = "nameFilter", required = true) String nameFilter, HttpServletResponse response) {

        if (nameFilter.equals("")) {
            throw new EmptyRequestParamException("The filter is empty.");
        }

        Optional<Contact> firstStreamContact;

        try (Stream<Contact> innerContactStream = contactService.findAllContacts()) {
            firstStreamContact = innerContactStream
                    .filter(contact -> !contact.getName().matches(nameFilter))
                    .findFirst();
            if (!firstStreamContact.isPresent()) {
                try (PrintWriter out = response.getWriter()) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.write("{contacts: [ ]}");
                    out.flush();
                } catch (Exception ex) {
                    throw new RuntimeException(ex.getMessage());
                }
            }
        }

        try (Stream<Contact> contactStream = contactService.findAllContacts()) {
            try (PrintWriter out = response.getWriter()) {
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{contacts: [ ");
                out.flush();
                contactStream
                        .filter(contact -> !contact.getName().matches(nameFilter))
                        .forEach(contact -> {
                            // skip writing ", " if it's first contact
                            if (!contact.equals(firstStreamContact.get())) {
                                out.write(", ");
                            }
                            out.write(new ContactAsJsonString(contact).getJsonString());
                            out.flush();
                        });
                out.write(" ]}");
                out.flush();
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
    }
}
