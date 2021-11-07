package ir.maktab.service;

import ir.maktab.model.entity.User;

import java.util.List;

public interface UserService {

    void changeLoginStatus(Integer newStatus , Integer id);

    User save(User user);

    void deleteById(Integer id);

    User findById(Integer id);

    List<User> findByName(String name);

    User findByLoginName(String l);

    User findByPassword(String p);

    User findByLoginNameAndPassword(String l, String p);

    User findByRole(Integer role);

    List<User> findAll();

    User updateUser(User user, Integer id);

    List<User> getUserList();
}
