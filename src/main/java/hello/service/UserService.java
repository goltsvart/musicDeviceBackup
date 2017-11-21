package hello.service;

import hello.data.TrackId;
import hello.data.domain.Album;
import hello.data.domain.Track;
import hello.data.domain.TrackList;
import hello.data.domain.User;
import hello.data.repo.AlbumRepository;
import hello.data.repo.TrackListRepository;
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
    private TrackListRepository trackListRepository;
    @Autowired
    private AlbumService albumService;

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
    public void createTrackList(User user, List<TrackList> lists) {
        user.setTrackLists(lists);
        trackListRepository.save(lists);
        userRepository.save(user);
    }
    public void addTracksList(List<TrackId> tracksId) {
        for(int k = 0; k < tracksId.size(); k++){
            if(!tracksId.get(k).getPlaylistId().isEmpty()
                    && !tracksId.get(k).getTrackId().isEmpty()) {
                String tId = tracksId.get(k).getTrackId();
                TrackList tl = trackListRepository.findTrackListByPlaylistId(tracksId.get(k).getPlaylistId());
                Track t = albumService.findTrackByTrackId(tId);
                albumService.saveTracksAndLists(tl, t);
            }
        }
    }
}