package hello.service;

import hello.data.domain.Album;
import hello.data.domain.Track;
import hello.data.domain.User;
import hello.data.repo.AlbumRepository;
import hello.data.repo.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private TrackRepository trackRepository;

    public List<Album> displayAll(User u) {
        List<Album> t = albumRepository.findAll();
        List<Album> userT = new ArrayList<Album>();
        for (int i = 0; i < t.size(); i++) {
            if (!u.getLibraryPersistanceId().equals(t.get(i).getLibraryPersistanceId())) {
                return null;
            } else {
                userT.add(t.get(i));
            }
        }
        return userT;
    }

    public Album findAlbumByAlbumName(String name){
        return albumRepository.findAlbumByAlbum(name);
    }

    public Track findTrackById(Long trackId){
        return trackRepository.findTrackByTrackId(trackId);
    }

    public void addTracksToAlbum(Album a, List<Track> tracks, Track t) {
        if(t == null){
            a.setTracks(tracks);
        }else{
            a.getTracks().add(t);
        }
        trackRepository.save(tracks);
        albumRepository.save(a);
    }
}
