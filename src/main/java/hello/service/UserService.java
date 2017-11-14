package hello.service;

import hello.data.domain.Track;
import hello.data.domain.User;
import hello.data.repo.TrackRepository;
import hello.data.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrackRepository trackRepository;


    @SuppressWarnings("unchecked")
    public void save(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void applyPersistanceId(User user, String persistanceId) {
        user.setLibraryPersistanceId(persistanceId);
        userRepository.save(user);
    }
    public void createLibrary(User user, List<Track> tracks) {
        user.setTrack(tracks);
        trackRepository.save(tracks);
        userRepository.save(user);
    }

}