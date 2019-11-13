/**
 * FileName: UserServiceImpl
 * Author:   huang.yj
 * Date:     2019/11/12 17:45
 * Description: 用户实现类
 */
package com.sample.test.service;

import com.sample.test.common.entity.User;
import com.sample.test.common.exception.ServiceException;
import com.sample.test.dao.UserDao;
import com.sample.test.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * 〈用户实现类〉
 *
 * @author huang.yj
 * @create 2019/11/12
 * @since 0.0.1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserById(int userId) throws ServiceException {
        try {
            Optional<User> op = userDao.findById(userId);
            return op.orElse(null);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void saveUser(User user) throws ServiceException {
        try {
            userDao.save(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    @Transactional
    public Integer updateUser(User user) throws ServiceException {
        try {
            return userDao.updateUsernameById(user.getUserName(), user.getId());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @Override
    public void deleteUserById(int id) throws ServiceException {
        try {
            userDao.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }
}