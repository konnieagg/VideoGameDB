package com.video.game.library.videoGameLibrary.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String root () {
        return ("Hello users!!");
    }



}
