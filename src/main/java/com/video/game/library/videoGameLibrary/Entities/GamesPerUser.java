package com.video.game.library.videoGameLibrary.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamesPerUser {

    private long id;
    private String username;
    private String gameid;
    private String gamestatus;


    public GamesPerUser() {
    }

    public GamesPerUser(long id, String username, String gameid) {
        this.id = id;
        this.username = username;
        this.gameid = gameid;
    }

    public GamesPerUser(String gameid) {
        this.gameid = gameid;
    }

    public GamesPerUser(long id, String username, String gameid, String gamestatus) {
        this.id = id;
        this.username = username;
        this.gameid = gameid;
        this.gamestatus = gamestatus;
    }
}
