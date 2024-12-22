package com.example.NaiduNetworking.Repo;

import com.example.NaiduNetworking.Models.Chat;
import com.example.NaiduNetworking.Models.ChatHistory;
import com.example.NaiduNetworking.Models.User;
import com.example.NaiduNetworking.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Mainrepo {
    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean insertUser(User user){
        String query = "INSERT INTO users VALUES (?,?,?)";
        int effected = 0;
        try{
            effected = jdbc.update(query, user.getUsername(), user.getPassword(), user.getDisplayname());
        }
        catch (Exception e){
            return false;
        }
        return effected > 0;
    }

    public Users getUserList(User user){
        String sql = "SELECT * FROM users WHERE username != ?";
        RowMapper<User> smap = (rs, rowNum) -> {
            User u = new User();
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setDisplayname(rs.getString("displayname"));
            return u;
        };
        Users userList = new Users();
        userList.setUsers(jdbc.query(sql, smap, user.getUsername()));
        return userList;
    }

    public boolean checkIfExists(User user){
        String sql = "SELECT * FROM users WHERE username = ?";
        RowMapper<User> smap = (rs, rowNum) -> {
            User u = new User();
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setDisplayname(rs.getString("displayname"));
            return u;
        };
        try{
            List<User> verify = jdbc.query(sql, smap, user.getUsername());
            if(verify.isEmpty())
                return false;
            else
                return verify.get(0).getPassword().equals(user.getPassword());
        }
        catch (Exception e){
            return false;
        }
    }
    public User getUser(String username){
        String sql = "SELECT * FROM users WHERE username = ?";
        RowMapper<User> smap = (rs, rowNum) -> {
            User u = new User();
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setDisplayname(rs.getString("displayname"));
            return u;
        };
        try{
            List<User> verify = jdbc.query(sql, smap, username);
            if(verify.isEmpty())
                return null;
            else
                return verify.getFirst();
        }
        catch (Exception e){
            return null;
        }
    }

    public String getDisplay(User user){
        String sql = "SELECT * FROM users WHERE username = ?";
        RowMapper<User> smap = (rs, rowNum) -> {
            User u = new User();
            u.setDisplayname(rs.getString("displayname"));
            return u;
        };
        List<User> verify = jdbc.query(sql, smap, user.getUsername());
        return verify.getFirst().getDisplayname();
    }

    public List<ChatHistory> getChats(String sender, String receiver){
        String query = "SELECT * FROM chats WHERE sender = ? AND receiver = ? OR sender = ? AND receiver = ?";
        RowMapper<ChatHistory> smap = (rs, rowNum) -> {
          ChatHistory history = new ChatHistory();
          history.setUsername(rs.getString("sender"));
          history.setMessage(rs.getString("msg"));
          history.setSelfSource(rs.getString("sender").equals(sender));
          return history;
        };
        List<ChatHistory> chatHistory;
        try{
            chatHistory = jdbc.query(query, smap, sender, receiver, receiver, sender);
        }
        catch (Exception e){
            return null;
        }
        return chatHistory;
    }

    public void insertMsg(Chat chat){
        String query = "INSERT INTO chats VALUES (?,?,?)";
        jdbc.update(query, chat.getSender(), chat.getReceiver(), chat.getMsg());
    }
}