package hello.service;

import hello.data.domain.Album;
import hello.data.domain.User;
import hello.data.repo.AlbumRepository;
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
    private AlbumRepository albumRepository;
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
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public void applyPersistanceId(User user, String persistanceId) {
        user.setLibraryPersistanceId(persistanceId);
        userRepository.save(user);
    }

    public void createLibrary(User user, List<Album> albums) {
        user.setAlbum(albums);
        albumRepository.save(albums);
        userRepository.save(user);
    }
}