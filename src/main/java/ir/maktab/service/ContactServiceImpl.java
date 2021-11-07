package ir.maktab.service;

import ir.maktab.exception.ResourceNotFoundException;
import ir.maktab.model.dao.ContactDao;
import ir.maktab.model.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Contact save(Contact contact) {
        Optional<Contact> found = contactDao.findById(contact.getId());
        if (!found.isPresent()) {
            return contactDao.save(contact);
        } else {
            System.out.println("contact exists");
            return found.get();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Contact> found = contactDao.findById(id);
        if (found.isPresent()) {
            contactDao.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Contact", "Id", id);
        }
    }

    @Override
    public void deleteAll(Iterable<Contact> contacts) {
        for (Contact c: contacts)
            contactDao.delete(c);
    }

    @Override
    public Contact findById(Integer id) {
        Optional<Contact> found = contactDao.findById(id);
        if (found.isPresent())
            return found.get();
        throw new ResourceNotFoundException("Contact", "Id", id);
    }

    @Override
    public List<Contact> findByName(String name) {
        List<Contact> found = contactDao.findByName(name);
        if (found.size() != 0)
            return found;
        throw new ResourceNotFoundException("Contacts", "Name", name);

    }

    @Override
    public List<Contact> findAll() {
        return contactDao.findAll();
    }

    @Override
    public List<Contact> findAllByUserId(Integer id) {
        return contactDao.findAllByUserId(id);
    }

    @Override
    public Contact updateContact(Contact contact, Integer id) {
        Optional<Contact> found = contactDao.findById(id);
        if (found.isPresent()) {
            Contact existContact = found.get();
            existContact.setName(contact.getName());
            existContact.setEmail(contact.getEmail());
            existContact.setPhone(contact.getPhone());
            existContact.setAddress(contact.getAddress());
            existContact.setRemark(contact.getRemark());
            contactDao.save(existContact);
            return existContact;
        }
        throw new ResourceNotFoundException("Contact", "Id", id);
    }

    public Page<Contact> findMaxMatch(String name, String phone, String email, int offset, int limit) {
        Pageable page = PageRequest.of(offset, limit);
        return contactDao.findAll(ContactDao.findMaxMatch(name, phone, email), page);
    }

}
