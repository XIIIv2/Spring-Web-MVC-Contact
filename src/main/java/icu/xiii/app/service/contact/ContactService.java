package icu.xiii.app.service.contact;

import icu.xiii.app.dto.contact.ContactDtoRequest;
import icu.xiii.app.entity.Contact;
import icu.xiii.app.service.BaseService;

import java.util.List;

public interface ContactService extends BaseService<Contact, ContactDtoRequest> {

    @Override
    boolean create(ContactDtoRequest request);

    @Override
    List<Contact> fetchAll();

    @Override
    Contact fetchById(Long id);

    @Override
    boolean update(Long id, ContactDtoRequest request);

    @Override
    boolean delete(Long id);
}
