package com.video.game.library.videoGameLibrary.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private long id;
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public User(long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
