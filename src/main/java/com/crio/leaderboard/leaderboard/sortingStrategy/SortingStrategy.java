package com.crio.leaderboard.leaderboard.sortingStrategy;

import com.crio.leaderboard.leaderboard.entities.User;

import java.util.*;

public interface SortingStrategy{
    List<User>sort(List<User>users);
}
