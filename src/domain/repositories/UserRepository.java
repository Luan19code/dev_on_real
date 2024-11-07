package domain.repositories;

import domain.entities.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);

    User getUserById(String id);

    List<User> getAllUsers();
}