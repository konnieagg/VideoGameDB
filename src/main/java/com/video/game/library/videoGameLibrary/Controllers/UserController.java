package com.video.game.library.videoGameLibrary.Controllers;

import com.video.game.library.videoGameLibrary.DataBases.UserDB;
import com.video.game.library.videoGameLibrary.Entities.User;
import com.video.game.library.videoGameLibrary.Services.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins="*")
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

//    @GetMapping ("/api/users/{id}")
//    public User userById (@PathVariable(name="id") long id) {
//        return userDB.findUserByiId(id);
//    }

    @PostMapping ("/api/userbyusername")
    public Object findUserByUsername ( @RequestBody User user) {
        if (userDB.findUserByUsername(user.getUsername(), user.getPassword()) == null) {
            return "User not found!";
        }
            return userDB.findUserByUsername(user.getUsername(), user.getPassword());

    }


    @PostMapping("/api/users")
    @ResponseBody
    public Object addNewUser (@RequestBody User user) {

        UserDB userDB = new UserDB();

        long id = userDB.addNewUser(user);
        if (id==0) {
            return "User already exists!";
        }
        user.setId(id);

        return user;
    }

    @DeleteMapping("/api/users/{username}")
    public int deleteUserById (@PathVariable(name="username") String username) {
        return userDB.deleteUser(username);

    }


}
