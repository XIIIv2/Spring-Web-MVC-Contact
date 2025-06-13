package icu.xiii.app.repository.contact;

import icu.xiii.app.dto.contact.ContactDtoRequest;
import icu.xiii.app.entity.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(ContactDtoRequest request) {
        Contact contact = request.getEntity();
        try {
            sessionFactory.getCurrentSession().persist(contact);
        } catch (Exception e) {
            return false;
        }
        return contact.getId() != null && contact.getId() > 0;
    }

    @Override
    public Optional<List<Contact>> fetchAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Contact> contacts = session
                    .createQuery("FROM Contact", Contact.class)
                    .getResultList();
            return Optional.of(contacts);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Contact> fetchById(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Contact contact = session.find(Contact.class, id);
            if (contact == null) {
                return Optional.empty();
            }
            return Optional.of(contact);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(Long id, ContactDtoRequest request) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Contact contact = request.getEntity();
            contact.setId(id);
            session.merge(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Contact contact = fetchById(id).orElse(null);
            if (contact != null) {
                session.remove(contact);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
