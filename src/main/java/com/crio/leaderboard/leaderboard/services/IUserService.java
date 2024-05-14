package com.crio.leaderboard.leaderboard.services;

import com.crio.leaderboard.leaderboard.entities.User;
import org.apache.coyote.BadRequestException;

import java.util.*;

public interface IUserService {
    User saveUser(User user);
    User updateUser(User user) throws BadRequestException;
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
    boolean existById(Long id);
}
