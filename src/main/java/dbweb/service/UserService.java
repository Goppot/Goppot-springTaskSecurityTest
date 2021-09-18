package dbweb.service;

import dbweb.model.User;

import java.util.List;

public interface UserService {

    void addUser(String name, int age, String email,  String password, String role);

    List<User> allUsers();

    User getUserById(int id);

    User getShowId();

    void updateUser(int id, String name, int age, String email,  String password, String role);

    void removeUser(int id);
}
