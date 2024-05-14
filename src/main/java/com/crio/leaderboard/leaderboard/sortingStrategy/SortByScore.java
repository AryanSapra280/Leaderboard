package com.crio.leaderboard.leaderboard.sortingStrategy;

import com.crio.leaderboard.leaderboard.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SortByScore implements SortingStrategy{

    @Override
    public List<User> sort(List<User> users) {
       List<User>sortedUser = users.stream().sorted((a,b)->{
           return b.getScore()-a.getScore();
       }).collect(Collectors.toList());
       return sortedUser;
    }
}
