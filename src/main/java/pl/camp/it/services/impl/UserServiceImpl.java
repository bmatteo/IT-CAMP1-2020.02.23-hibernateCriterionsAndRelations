package pl.camp.it.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.dao.IUserDAO;
import pl.camp.it.model.User;
import pl.camp.it.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public void persistUser(User user) {
        this.userDAO.persistUser(user);
    }

    @Override
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }
}
