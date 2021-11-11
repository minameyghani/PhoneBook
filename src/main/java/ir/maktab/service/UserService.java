package ir.maktab.service;

import ir.maktab.exception.UserBlockedException;
import ir.maktab.model.entity.Contact;
import ir.maktab.model.entity.User;

import java.util.List;

public interface UserService {

    void changeLoginStatus(Integer newStatus , Integer id);

    void register(User user);

    void deleteById(Integer id);

    User findById(Integer id);

    List<User> findByName(String name);

    User findByLoginName(String l);

    User findByPassword(String p);

    User login(String l, String p) throws UserBlockedException;

    User findByRole(Integer role);

    List<User> findAll();

    User updateUser(User user, Integer id);

    List<User> getUserList();

    List<User> findMaxMatch(String name, String phone, String email);
}
