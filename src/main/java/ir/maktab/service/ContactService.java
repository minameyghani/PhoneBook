package ir.maktab.service;

import ir.maktab.model.entity.Contact;
import ir.maktab.model.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactService {
    Contact addContact(Contact contact);

    void deleteById(Integer id);

    void deleteAll(Integer[] contactIds);

    Contact findById(Integer id);

    List<Contact> findByName(String name);

    List<Contact> findAll();

    List<Contact> findAllByUserId(User user);

   Contact updateContact(Contact c, Integer id);

    public Page<Contact> findMaxMatch(String name, String phone, String email, Integer id, int offset, int limit);
}
