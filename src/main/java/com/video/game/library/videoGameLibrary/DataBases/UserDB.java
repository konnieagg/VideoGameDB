package com.video.game.library.videoGameLibrary.DataBases;

import com.video.game.library.videoGameLibrary.Entities.User;
import com.video.game.library.videoGameLibrary.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDB {

    public List<User> findAll(int limit, int offset) {

        List<User> users = new ArrayList<>();

        try {
            Connection connection = MyConnection.getConnection();
            String sql = "SELECT * FROM users LIMIT ? OFFSET ? ";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,limit);
            preparedStatement.setInt(2,offset);

            ResultSet result=preparedStatement.executeQuery();

            while(result.next()) {
                long id = result.getLong("ID");
                String username = result.getString("username");
                String password = result.getString("password");
                String email = result.getString("email");

                User user = new User(id, username, password, email );

                users.add(user);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return users;
    }

    public User findUserByiId (long id) {
        User userById=new User();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = ("SELECT * from users WHERE id =?");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            String username = result.getString("username");
            String password = result.getString("password");
            String email = result.getString("email");

            userById = new User(id, username, password, email);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return userById;

        }

    public User findUserByUsername (String username,String password) {
        User userByUsername=new User();
        try {
            Connection connection = MyConnection.getConnection();
            String sql = ("SELECT * from users WHERE username = ? and password = ? ");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            long id = result.getLong("ID");

            String email = result.getString("email");

            userByUsername = new User(id, username, password, email);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return userByUsername;

    }

    public long addNewUser (User user) {
        long id = 0;
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "INSERT INTO users VALUES (NULL,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getEmail());

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

    public int deleteById (long id)  {
        int numberOfUsersDeleted=0;
        try {
            Connection connection = MyConnection.getConnection();
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            numberOfUsersDeleted = preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return numberOfUsersDeleted;
    }



    public int countUsers () {
        int count= 0;
        try {
            Connection connection=MyConnection.getConnection();
            Statement statement=connection.createStatement();
            ResultSet result=statement.executeQuery("SELECT COUNT(*) AS total FROM users");
            result.next();
            count=result.getInt("total");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return count;
    }



}
