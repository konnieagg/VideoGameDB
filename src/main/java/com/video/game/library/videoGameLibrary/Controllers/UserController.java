package com.video.game.library.videoGameLibrary.Controllers;

import com.video.game.library.videoGameLibrary.DataBases.UserDB;
import com.video.game.library.videoGameLibrary.Entities.User;
import com.video.game.library.videoGameLibrary.Services.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    UserDB userDB = new UserDB();
    UserService userService= new UserService();

    @GetMapping("/api/users")
    public Map<String, Object> allUsers(
            @RequestParam(name = "limit", defaultValue = "20") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset) {

        Map<String, Object> result= new HashMap<>();
        result.put("count", userDB.countUsers());
        result.put("result",userDB.findAll(limit,offset));
        result.put("next", userService.getNextUrl(limit, offset));
        result.put("previous",userService.getPreviousUrl(limit, offset));

        return result;

    }

    @GetMapping ("/api/users/{id}")
    public User userById (@PathVariable(name="id") long id) {
        return userDB.findUserByiId(id);
    }

    @PostMapping("/api/users")
    @ResponseBody
    public User addNewUser (@RequestBody User user) {

        UserDB userDB = new UserDB();
        long id = userDB.addNewUser(user);

        user.setId(id);

        return user;
    }

    @DeleteMapping("/api/users/{id}")
    public int deleteUserById (@PathVariable(name="id") long id) {
        return userDB.deleteById(id);

    }


}
