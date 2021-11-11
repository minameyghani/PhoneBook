package ir.maktab.service;

import ir.maktab.exception.ResourceNotFoundException;
import ir.maktab.model.dao.ContactDao;
import ir.maktab.model.entity.Contact;
import ir.maktab.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    private final ContactDao contactDao;
    @PersistenceContext
    private EntityManager em;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Contact addContact(Contact contact) {
        return contactDao.save(contact);

    }

    @Override
    public void deleteById(Integer id) {
        contactDao.deleteById(id);
    }

    @Override
    public void deleteAll(Iterable<Contact> contacts) {
        for (Contact c : contacts)
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
    public List<Contact> findAllByUserId(User user) {
        return contactDao.findAllByUserId(user.getId());
    }

    @Override
    public Contact updateContact(Contact c, Integer id) {
        Contact found = contactDao.getOne(id);
        found.setName(c.getName());
        found.setPhone(c.getPhone());
        found.setEmail(c.getEmail());
        found.setAddress(c.getAddress());
        found.setRemark(c.getRemark());
        return contactDao.save(found);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Contact> findMaxMatch(String name, String phone, String email,Integer id,int offset, int limit) {
        Pageable of= PageRequest.of(offset,limit);
        return contactDao.findAll(ContactDao.findMaxMatch(name, phone, email,id),of);
    }

}
