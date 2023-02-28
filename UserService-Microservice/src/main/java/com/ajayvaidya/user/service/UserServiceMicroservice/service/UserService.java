package com.ajayvaidya.user.service.UserServiceMicroservice.service;

import com.ajayvaidya.user.service.UserServiceMicroservice.entitiy.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);

    String deleteUserById(String userId);

}
