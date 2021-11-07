package ir.maktab.service;

import ir.maktab.exception.ResourceNotFoundException;
import ir.maktab.exception.UserBlockedException;
import ir.maktab.model.dao.UserDao;
import ir.maktab.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User save(User user) {
        Optional<User> found = userDao.findById(user.getId());
        if (!found.isPresent()) {
            return userDao.save(user);
        } else {
            System.out.println("user exists");
            return found.get();
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
    public User findByLoginNameAndPassword(String l, String p) {
        Optional<User> found = userDao.findByLoginNameAndPassword(l, p);
        try {
            if (found.isPresent()) {
                User user = found.get();
                if (userDao.findByLoginStatus(user.getLoginStatus()) == LOGIN_STATUS_ACTIVE) {
                    return user;
                } else {
                    throw new UserBlockedException("user is blocked");
                }
            }

        } catch (UserBlockedException userBlockedException) {
            userBlockedException.getMessage();
        }
        System.out.println("User not found");
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
    public List<User> getUserList() {
        Optional<User> adminFound = userDao.findByRole(ROLE_ADMIN);
        if (adminFound.isPresent()) {
            return findAll();
        }
        return null;
    }

    @Override
    public void changeLoginStatus(Integer newStatus, Integer id) {
        userDao.updateStatus(newStatus, id);
    }


    public Page<User> findMaxMatch(String name, String phone, String email, int offset, int limit) {
        Pageable page = PageRequest.of(offset, limit);
        return userDao.findAll(UserDao.findMaxMatch(name, phone, email), page);
    }

}
