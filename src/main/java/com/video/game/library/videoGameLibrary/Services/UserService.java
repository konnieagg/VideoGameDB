package com.video.game.library.videoGameLibrary.Services;

import com.video.game.library.videoGameLibrary.DataBases.UserDB;

public class UserService {
    public String getNextUrl (int limit, int offset) {
        UserDB userDB=new UserDB();
        int total=userDB.countUsers();
        int newOffset=offset+limit;
        if (newOffset > total) {
            return null;
        }
        return String.format("http://localhost:8080/api/users?limit=%d&offset=%d", limit, newOffset);
    }

    public String getPreviousUrl (int limit, int offset) {
        if (offset == 0) {
            return null;
        }
        int newOffset=offset-limit;
        if (newOffset<0) {
            newOffset=0;
        }
        return String.format("http://localhost:8080/api/users?limit=%d&offset=%d", limit, newOffset);
    }

}
