package com.crio.leaderboard.leaderboard.entities;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.util.*;

@Document(collection = "user")
public class User {
    @Id
    private Long id;

    @NotNull(message = "Name can not be null")
    @NotEmpty(message = "Name can not be empty")
    private String userName;

    @Min(value = 0, message = "value should be between 1 and 100")
    @Max(value = 100, message = "value should be between 1 and 100")
    private Integer score;
    private Set<String>badges;

    public User(Long id, String userName, Integer score, Set<String> badges) {
        this.id = id;
        this.userName = userName;
        this.score = score;
        this.badges = badges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }

    public Set<String> getBadges() {
        return badges;
    }

    public void setBadges(Set<String> badges) {
        this.badges = badges;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", score=" + score +
                ", badges=" + badges +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(score, user.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, score, badges);
    }
}
