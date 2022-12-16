package com.video.game.library.videoGameLibrary;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

    public static Connection getConnection () throws Exception {

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7584938",
                "sql7584938",
                "JNrQQ7n6wY");


        return connection;
    }

}
