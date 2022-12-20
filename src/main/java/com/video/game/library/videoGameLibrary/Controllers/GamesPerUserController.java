package com.video.game.library.videoGameLibrary.Controllers;
import com.video.game.library.videoGameLibrary.DataBases.GamesPerUserDB;
import com.video.game.library.videoGameLibrary.Entities.GamesPerUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
public class GamesPerUserController {

    GamesPerUserDB gamesPerUserDB = new GamesPerUserDB();

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

    @GetMapping ("/api/game/{username}")
    public List<String> gameByUserUsername (@PathVariable(name="username") String username) {

        return gamesPerUserDB.findGamesByUser(username);
    }

    @DeleteMapping("/api/delete/{username}/{gameid}")
    public Object deleteGame (
            @PathVariable(name="username") String username,
            @PathVariable(name="gameid") String gameid) {
        if (gamesPerUserDB.deleteGame(username, gameid ) == 0) {
            return "Game not found!!!";
        }return gamesPerUserDB.deleteGame(username, gameid);
    }







}
