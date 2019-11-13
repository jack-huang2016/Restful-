package com.sample.test.service.interfaces;

import com.sample.test.common.entity.User;
import com.sample.test.common.exception.ServiceException;

public interface UserService {

    public User queryUserById(int userId) throws ServiceException;

    public void saveUser(User user) throws ServiceException;

    public Integer updateUser(User user) throws ServiceException;

    public void deleteUserById(int id) throws ServiceException;
}
