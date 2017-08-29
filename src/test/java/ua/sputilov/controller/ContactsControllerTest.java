package ua.sputilov.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ua.sputilov.ContactSystemApplicationTests;
import ua.sputilov.model.Contact;
import ua.sputilov.service.ContactService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContactsControllerTest extends ContactSystemApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private ContactService contactService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Transactional(readOnly = true)
    public void searchContactsWithNegativeFilterByNameFirstRegExpTest() {

        List<Contact> contacts;

        String filter = "^.*[-].*$";

        contacts = contactService.findAllContacts()
                .filter(contact -> !contact.getName().matches(filter))
                .collect(Collectors.toList());

        try {
            mockMvc.perform(
                    get("/hello/contacts")
                            .param("nameFilter", filter))
                    .andExpect(jsonPath("$.contacts.length()").value(contacts.size()))
                    .andExpect(status().isOk());

        } catch (Exception ex) {
        }
    }

    @Test
    @Transactional(readOnly = true)
    public void searchContactsWithNegativeFilterByNameSecondRegExpTest() {

        List<Contact> contacts;

        String filter = "^S.*$";

        contacts = contactService.findAllContacts()
                .filter(contact -> !contact.getName().matches(filter))
                .collect(Collectors.toList());

        try {
            mockMvc.perform(
                    get("/hello/contacts")
                            .param("nameFilter", filter))
                    .andExpect(jsonPath("$.contacts.length()").value(contacts.size()))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
        }
    }

    @Test
    public void searchContactsWithNegativeFilterByNameEmptyFilterTest() {

        String filter = "";

        try {
            mockMvc.perform(
                    get("/hello/contacts")
                            .param("nameFilter", filter))
                    .andExpect(jsonPath("$.errors").value("The filter is empty."))
                    .andExpect(status().isBadRequest());
        } catch (Exception ex) {
        }
    }
}
