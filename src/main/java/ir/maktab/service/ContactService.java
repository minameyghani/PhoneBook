package ir.maktab.service;

import ir.maktab.model.dao.ContactDao;
import ir.maktab.model.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactService {
    @Autowired
    private ContactDao contactDao;

    public void registerUser(Contact contact) {
        Optional<Contact> found = contactDao.findById(contact.getId());
        if (!found.isPresent())
            contactDao.save(contact);
    }

    public void deleteUser(Contact contact) {
        Optional<Contact> found = contactDao.findById(contact.getId());
        if (found.isPresent())
            contactDao.delete(contact);
    }

    public void deleteUserById(Integer id) {
        contactDao.deleteById(id);
    }

    public List<Contact> findAll() {
        return contactDao.findAll();
    }

}
