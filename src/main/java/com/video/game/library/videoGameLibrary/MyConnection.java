package com.video.game.library.videoGameLibrary;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

    public static Connection getConnection () throws Exception {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/video_game_library",
                "root",
                "underdark01!");

        return connection;
    }

}
