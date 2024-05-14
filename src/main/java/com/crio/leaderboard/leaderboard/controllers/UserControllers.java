package com.crio.leaderboard.leaderboard.controllers;

import com.crio.leaderboard.leaderboard.entities.User;
import com.crio.leaderboard.leaderboard.exceptions.UserNotFound;
import com.crio.leaderboard.leaderboard.services.UserService;
import com.crio.leaderboard.leaderboard.sortingStrategy.SortByScore;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
@RequestMapping("/users")
public class UserControllers {

    @Autowired
    private UserService userService;
    @Autowired
    private SortByScore sortByScore;
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        userService.setSortingStrategy(sortByScore);
        List<User> users =userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {
        User user = null;
        try {
            user = userService.getUserById(userId);
        }catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        if(userService.existById(user.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist with this id");
        }
        user.setScore(0);
        user.setBadges(new HashSet<>());
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(savedUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody User user, BindingResult result) {
        User updatedUser = null;
        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.toString());
        }
        try {
            user.setId(userId);
            updatedUser = userService.updateUser(user);
        }catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }catch (BadRequestException badRequestException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Score is less than equal to zero or multiple values" +
                    " are being tried to update");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        try{
            userService.deleteUserById(userId);
        }catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
