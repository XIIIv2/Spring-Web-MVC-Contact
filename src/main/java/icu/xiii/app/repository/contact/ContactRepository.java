package icu.xiii.app.repository.contact;

import icu.xiii.app.dto.contact.ContactDtoRequest;
import icu.xiii.app.entity.Contact;
import icu.xiii.app.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends BaseRepository<Contact, ContactDtoRequest> {

    boolean create(ContactDtoRequest request);
    Optional<List<Contact>> fetchAll();
    Optional<Contact> fetchById(Long id);
    boolean update(Long id, ContactDtoRequest request);
    boolean delete(Long id);
}
