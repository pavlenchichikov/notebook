package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;

import java.util.List;

public class UserController {
    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        return repository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public List<String> readAll() {
        return repository.readAll();
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}