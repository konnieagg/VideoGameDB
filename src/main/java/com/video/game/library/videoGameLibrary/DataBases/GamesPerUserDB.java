package com.video.game.library.videoGameLibrary.DataBases;

import com.video.game.library.videoGameLibrary.Entities.GamesPerUser;
import com.video.game.library.videoGameLibrary.Entities.User;
import com.video.game.library.videoGameLibrary.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<GamesPerUser> findGamesByUser (String username) {
        List<GamesPerUser> gamesPerUserById = new ArrayList();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = ("SELECT * from GamesPerUSer WHERE username =?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                long id = result.getLong("ID");
                String gameid = result.getString("gameid");

                gamesPerUserById.add( new GamesPerUser(id, username, gameid));
            }

        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return gamesPerUserById;

    }


}
