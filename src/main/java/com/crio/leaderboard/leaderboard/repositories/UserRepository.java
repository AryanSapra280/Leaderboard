package com.crio.leaderboard.leaderboard.repositories;

import com.crio.leaderboard.leaderboard.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Long> {
}
