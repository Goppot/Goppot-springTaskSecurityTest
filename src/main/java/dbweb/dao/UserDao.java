package dbweb.dao;

import dbweb.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<User> allUsers();

    User showUserById(int id);

    User getUserByEmail(String email);

    void updateUser(int id, User usr);

    void deleteUser(int id);

    void removeUser(User user);
}

