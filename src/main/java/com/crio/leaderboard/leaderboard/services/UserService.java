package com.crio.leaderboard.leaderboard.services;

import com.crio.leaderboard.leaderboard.entities.User;
import com.crio.leaderboard.leaderboard.exceptions.UserNotFound;
import com.crio.leaderboard.leaderboard.factory.Badges;
import com.crio.leaderboard.leaderboard.repositories.UserRepository;
import com.crio.leaderboard.leaderboard.sortingStrategy.SortingStrategy;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    private SortingStrategy sortingStrategy;
    @Override
    public User saveUser(User user) {
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    @Override
    public User updateUser(User user) throws UserNotFound, BadRequestException{
        User dbUser = getUserById(user.getId());
        User tempUser = new User(user.getId(),user.getUserName(), user.getScore(), user.getBadges());
        tempUser.setScore(dbUser.getScore());
        if(!(tempUser.equals(dbUser)) || user.getScore() == 0) {
            throw new BadRequestException();
        }

        String badge = Badges.getBadge(user.getScore());
        Set<String>badges = dbUser.getBadges();
        badges.add(badge);

        dbUser.setScore(dbUser.getScore() + user.getScore());
        dbUser.setBadges(badges);
        return saveUser(dbUser);
    }

    @Override
    public List<User> getAllUsers() {
        List<User>users = userRepository.findAll();
        List<User>sortedUsers = sortingStrategy.sort(users);
        return sortedUsers;
    }

    @Override
    public User getUserById(Long id) throws UserNotFound{
        Optional<User>optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) {
            throw new UserNotFound(String.format("No User found with id %d",id));
        }
        return optionalUser.get();
    }

    @Override
    public void deleteUserById(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public boolean existById(Long id) {
        try {
            getUserById(id);
            return true;
        }catch (UserNotFound userNotFound) {
            return false;
        }
    }
    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

}
