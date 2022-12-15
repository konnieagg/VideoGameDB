package com.video.game.library.videoGameLibrary.DataBases;

import com.video.game.library.videoGameLibrary.Entities.GamesPerUser;
import com.video.game.library.videoGameLibrary.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GamesPerUserDB {
    public long addNewGame(GamesPerUser gamesPerUser) {
        long id = 0;
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "INSERT INTO GamesPerUSer VALUES (NULL,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,gamesPerUser.getUsername());
            preparedStatement.setString(2,gamesPerUser.getGameid());


            preparedStatement.executeUpdate();

            ResultSet keys=preparedStatement.getGeneratedKeys();
            keys.next();
            id=keys.getLong(1);


        }catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
        return id;
    }

}
