package com.crio.leaderboard.leaderboard.factory;

public class Badges {
    private Badges() {
        ;
    }
    public static String getBadge(Integer score) {
        String badge = "";
        if(score>=0 && score<30) {
            badge = "Code Ninja";
        }
        else if(score>=30 && score<60) {
            badge = "Code Champ";
        }
        else {
            badge = "Code Master";
        }
        return badge;
    }
}
