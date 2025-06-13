package icu.xiii.app.service.contact;

import icu.xiii.app.dto.contact.ContactDtoRequest;
import icu.xiii.app.entity.Contact;
import icu.xiii.app.repository.contact.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Override
    @Transactional
    public boolean create(ContactDtoRequest request) {
        return repository.create(request);
    }

    @Override
    @Transactional
    public List<Contact> fetchAll() {
        return repository.fetchAll().orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Contact fetchById(Long id) {
        return repository.fetchById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean update(Long id, ContactDtoRequest request) {
        return repository.update(id, request);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
