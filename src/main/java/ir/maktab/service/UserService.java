package ir.maktab.service;

import ir.maktab.model.dao.UserDao;
import ir.maktab.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void registerUser(User user) {
        Optional<User> found = userDao.findById(user.getId());
        if (!found.isPresent())
            userDao.save(user);
    }

    public void deleteUser(User user) {
        Optional<User> found = userDao.findById(user.getId());
        if (found.isPresent())
            userDao.delete(user);
    }

    public void deleteUserById(Integer id) {
        userDao.deleteById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }


}
