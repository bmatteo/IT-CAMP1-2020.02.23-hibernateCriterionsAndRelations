package pl.camp.it.dao;

import pl.camp.it.model.User;

public interface IUserDAO {
    void persistUser(User user);
    User getUserById(int id);
    User getUserByLogin(String login);
}
