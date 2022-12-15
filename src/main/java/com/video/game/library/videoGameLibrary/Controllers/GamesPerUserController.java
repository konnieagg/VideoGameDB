package com.video.game.library.videoGameLibrary.Controllers;


import com.video.game.library.videoGameLibrary.DataBases.GamesPerUserDB;
import com.video.game.library.videoGameLibrary.DataBases.UserDB;
import com.video.game.library.videoGameLibrary.Entities.GamesPerUser;
import com.video.game.library.videoGameLibrary.Entities.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
public class GamesPerUserController {



    @PostMapping("/api/game")
    @ResponseBody
    public Object addNewGame (@RequestBody GamesPerUser gamesPerUser) {

        GamesPerUserDB gamesPerUserDB = new GamesPerUserDB();

        long id = gamesPerUserDB.addNewGame(gamesPerUser);
        if (id==0) {
            return "Game already exists!";
        }
        gamesPerUser.setId(id);

        return gamesPerUser;
    }




}
