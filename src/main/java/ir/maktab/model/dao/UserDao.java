package ir.maktab.model.dao;

import ir.maktab.model.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserDao extends Repository<User,Integer> {
    void save(User u);
    void delete(User u);
    void deleteById(Integer id);
    Optional<User> findById(Integer id);
    List<User> findByName(String name);
    Optional<User> findByLoginName(String l);
    Optional<User> findByPassword(String p);
    List<User> findAllById();
    List<User> findAll();
}
