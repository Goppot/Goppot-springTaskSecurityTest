package dbweb.service;

import dbweb.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> allUsers();

    User showUserById(int id);

    void updateUser(int id, User usr);

    void deleteUser(int id);

    void removeUser(User user);
}
