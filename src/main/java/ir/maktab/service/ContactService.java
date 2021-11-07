package ir.maktab.service;

import ir.maktab.model.entity.Contact;

import java.util.List;

public interface ContactService {
    Contact save(Contact contact);

    void deleteById(Integer id);

    void deleteAll(Iterable<Contact> contacts);

    Contact findById(Integer id);

    List<Contact> findByName(String name);

    List<Contact> findAll();

    List<Contact> findAllByUserId(Integer id);

    Contact updateContact(Contact contact, Integer id);
}
