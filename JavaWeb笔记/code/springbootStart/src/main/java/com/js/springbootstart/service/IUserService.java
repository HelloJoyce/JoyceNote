package com.js.springbootstart.service;

import com.js.springbootstart.pojo.User;
import com.js.springbootstart.pojo.dto.UserDto;

public interface IUserService {
    /**
     * insert user
     * @param user
     */
    User addUser(UserDto user);

    User updateUser(UserDto user);

    User getUserById(Integer userId);

    void deleteUserById(Integer userId);

}
