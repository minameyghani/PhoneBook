package ir.maktab.model.dao;

import ir.maktab.model.entity.Contact;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
@org.springframework.stereotype.Repository
public interface ContactDao extends Repository<Contact, Integer> {
    void save(Contact u);
    void delete(Contact u);
    void deleteById(Integer id);
    Optional<Contact> findById(Integer id);
    List<Contact> findAllById();
    List<Contact> findAll();
}
