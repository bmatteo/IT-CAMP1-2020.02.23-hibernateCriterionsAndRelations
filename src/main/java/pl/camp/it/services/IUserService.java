package pl.camp.it.services;

import pl.camp.it.model.User;

public interface IUserService {
    void persistUser(User user);
    User getUserById(int id);
}
