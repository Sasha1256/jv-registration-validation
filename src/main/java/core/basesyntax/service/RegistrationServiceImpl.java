package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.User;

import java.util.ArrayList;
import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) throws ImpossibleRegisterUserException {
        if (user == null) {
            throw new ImpossibleRegisterUserException("The user can`t be empty");
        } else if (user.getLogin() == null || user.getLogin().length() < 6) {
            throw new ImpossibleRegisterUserException("This login is incorrect");
        } else if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new ImpossibleRegisterUserException("This password is incorrect");
        } else if (user.getAge() == null || user.getAge() < 18) {
            throw new ImpossibleRegisterUserException("Age of the user isn`t enough to show content");
        } else {
            List<User> copy = new ArrayList<>(Storage.people);
            for (int i = 0; i < copy.size(); i++) {
                if (copy.get(i).getLogin().equals(user.getLogin())) {
                    throw new ImpossibleRegisterUserException("The user who have this login already exists");
                }
            }
            storageDao.add(user);
        }
        return user;
    }
}
