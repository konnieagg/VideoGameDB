package com.video.game.library.videoGameLibrary.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamesPerUser {

    private long id;
    private String username;
    private String gameid;


    public GamesPerUser() {
    }

    public GamesPerUser(long id, String username, String gameid) {
        this.id = id;
        this.username = username;
        this.gameid = gameid;
    }
}
