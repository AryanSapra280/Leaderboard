package com.crio.leaderboard.leaderboard.services;

import com.crio.leaderboard.leaderboard.entities.User;
import com.crio.leaderboard.leaderboard.repositories.UserRepository;
import com.crio.leaderboard.leaderboard.sortingStrategy.SortByScore;
import com.crio.leaderboard.leaderboard.sortingStrategy.SortingStrategy;
import com.mongodb.assertions.Assertions;
import jakarta.validation.constraints.AssertTrue;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class UserServiceTest {

    @Mock
    public UserRepository userRepository;
    private static SortingStrategy sortingStrategy;

    @InjectMocks
    public UserService userService;

    @BeforeAll
    public static void setup() {
        sortingStrategy = new SortByScore();
    }

    @Test
    public void saveUserTest() {
        User expectedUser = new User();
        expectedUser.setUserName("aryan");
        expectedUser.setBadges(new HashSet<>());
        expectedUser.setScore(0);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(expectedUser);
        User actualUser = userService.saveUser(expectedUser);
        Assertions.assertTrue(expectedUser.getUserName().equals(actualUser.getUserName()));
        Assertions.assertTrue(expectedUser.getScore().equals(actualUser.getScore()));
    }

    @Test
    public void updateUserTest() throws BadRequestException {
        Long id = 14L;
        Set<String> beforeUpdatedBadges = new HashSet<>();
        Set<String> afterUpdatedBadges = new HashSet<>();
        beforeUpdatedBadges.add("Code Ninja");
        afterUpdatedBadges.add("Code Ninja");
        User mockedUser = new User(id,"aryan",10,beforeUpdatedBadges);
        User withUpdatedScore = new User(id,"dump",30,beforeUpdatedBadges);
        afterUpdatedBadges.add("Code Champ");
        User expectedUser = new User(id,"aryan",40,afterUpdatedBadges);
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(mockedUser));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(expectedUser);

        User actualUser = userService.updateUser(withUpdatedScore);
        Assertions.assertTrue(expectedUser.getBadges().size() == actualUser.getBadges().size());
        Assertions.assertTrue(expectedUser.getScore() == actualUser.getScore());
    }

}
