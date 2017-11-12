package hello.service;

import hello.data.domain.User;
import hello.data.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("unchecked")
    public void save(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}