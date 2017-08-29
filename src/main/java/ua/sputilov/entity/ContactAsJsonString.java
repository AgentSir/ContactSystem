package ua.sputilov.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.sputilov.model.Contact;

public class ContactAsJsonString {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    private String jsonString;

    public ContactAsJsonString(Contact contact) {
        this.jsonString = getContactAsJsonString(contact);
    }

    private String getContactAsJsonString(Contact contact) {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(contact)
                    .toString();
        } catch (JsonProcessingException ex) {
            LOG.error("Error: " + ex);
        }
        return null;
    }

    public String getJsonString() {
        return jsonString;
    }
}
