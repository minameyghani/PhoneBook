package ir.maktab.service;

import ir.maktab.exception.ResourceNotFoundException;
import ir.maktab.exception.UserBlockedException;
import ir.maktab.model.dao.UserDao;
import ir.maktab.model.entity.Contact;
import ir.maktab.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    public static final Integer LOGIN_STATUS_ACTIVE = 1;
    public static final Integer LOGIN_STATUS_BLOCKED = 2;
    public static final Integer ROLE_ADMIN = 1;
    public static final Integer ROLE_USER = 2;

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void register(User user) {
        Optional<User> found = userDao.findByLoginName(user.getLoginName());
        if (!found.isPresent()) {
            userDao.save(user);
        } else {
            throw new DuplicateKeyException("Username already registered.Please select another one.");
        }
    }

    @Override
    public void deleteById(Integer id) {
        Optional<User> found = userDao.findById(id);
        if (found.isPresent()) {
            userDao.deleteById(id);
        } else {
            throw new ResourceNotFoundException("User", "Id", id);
        }
    }

    @Override
    public User findById(Integer id) {
        Optional<User> found = userDao.findById(id);
        if (found.isPresent())
            return found.get();
        throw new ResourceNotFoundException("User", "Id", id);
    }

    @Override
    public List<User> findByName(String name) {
        List<User> found = userDao.findByName(name);
        if (found.size() != 0)
            return found;
        throw new ResourceNotFoundException("Users", "Name", name);
    }

    @Override
    public User findByLoginName(String l) {
        Optional<User> found = userDao.findByLoginName(l);
        if (found.isPresent())
            return found.get();
        throw new ResourceNotFoundException("User", "Login Name", l);
    }

    @Override
    public User findByPassword(String p) {
        Optional<User> found = userDao.findByPassword(p);
        if (found.isPresent())
            return found.get();
        throw new ResourceNotFoundException("User", "Password", p);

    }

    @Override
    public User login(String l, String p) throws UserBlockedException {
        Optional<User> found = userDao.findByLoginNameAndPassword(l, p);
        if (found.isPresent()) {
            User user = found.get();
            if (user.getLoginStatus() == 1) {
                return user;
            } else {
                throw new UserBlockedException("user is blocked");
            }
        }
        return null;
    }

    @Override
    public User findByRole(Integer role) {
        Optional<User> found = userDao.findByRole(role);
        return found.get();
    }


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User updateUser(User user, Integer id) {
        Optional<User> found = userDao.findById(id);
        if (found.isPresent()) {
            User existUser = found.get();
            existUser.setName(user.getName());
            existUser.setEmail(user.getEmail());
            existUser.setPhone(user.getPhone());
            existUser.setAddress(user.getAddress());
            existUser.setLoginName(user.getLoginName());
            existUser.setPassword(user.getPassword());
            userDao.save(existUser);
            return existUser;
        }
        throw new ResourceNotFoundException("User", "Id", id);
    }

    @Override
    public List<User> findAllUsers(Integer i) {
        return userDao.findAllByRole(2);
    }

    @Override
    public void changeLoginStatus(Integer newStatus, Integer id) {
        userDao.updateStatus(newStatus, id);
    }

    @Override
    public boolean isUserNameExist(String username) {
        Optional<User> found = userDao.findByLoginName(username);
        if (found.isPresent())
            return true;
        return false;
    }

    @Override
    public List<User> findMaxMatch(String name, String phone, String email) {
        return userDao.findAll(UserDao.findMaxMatch(name, phone, email));
    }

}
