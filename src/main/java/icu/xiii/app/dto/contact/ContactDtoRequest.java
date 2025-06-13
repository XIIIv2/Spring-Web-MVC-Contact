package icu.xiii.app.dto.contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import icu.xiii.app.entity.Contact;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContactDtoRequest(Long id, String firstName, String lastName, String phone) {

    public Contact getEntity() {
        return new Contact(id, firstName, lastName, phone);
    }
}
