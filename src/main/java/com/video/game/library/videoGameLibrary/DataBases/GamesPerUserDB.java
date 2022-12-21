package com.video.game.library.videoGameLibrary.DataBases;

import com.video.game.library.videoGameLibrary.Entities.GamesPerUser;
import com.video.game.library.videoGameLibrary.Entities.User;
import com.video.game.library.videoGameLibrary.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamesPerUserDB {
    public long addNewGame(GamesPerUser gamesPerUser) {
        long id = 0;
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "INSERT INTO GamesPerUSer VALUES (NULL,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,gamesPerUser.getUsername());
            preparedStatement.setString(2,gamesPerUser.getGameid());
            preparedStatement.setString(3,gamesPerUser.getGamestatus());



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

    public Map<String, Object> findGamesByUser (String username) {
        Map<String, Object> gamesPerUserById = new HashMap<>();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = ("SELECT gameid,gamestatus from GamesPerUSer WHERE username =?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
//                long id = result.getLong("ID");
                String gameid = result.getString("gameid");
                String gamestatus = result.getString("gamestatus");


                gamesPerUserById.put(gameid, gamestatus);
            }

        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return gamesPerUserById;

    }

    public int deleteGame (String username, String gameid)  {
        int numberOfGamesDeleted=0;
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "DELETE FROM GamesPerUSer WHERE username=? AND gameid=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,username);

            preparedStatement.setString(2,gameid);
            numberOfGamesDeleted = preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return numberOfGamesDeleted;
    }

    public int updateGame(String gamestatus, String username, String gameid) {
        int numberOfGamesUpdated=0;
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "UPDATE gamesperuser SET gamestatus=? WHERE username=? AND gameid=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,gamestatus);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,gameid);

            preparedStatement.executeUpdate();


        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return numberOfGamesUpdated;
    }



}
