package hello.service;

import hello.data.domain.Album;
import hello.data.domain.Track;
import hello.data.domain.TrackList;
import hello.data.domain.User;
import hello.data.repo.AlbumRepository;
import hello.data.repo.TrackListRepository;
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
    @Autowired
    private TrackListRepository trackListRepository;

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

    public List<Track> displayAllTracks(User u, Album a) {
        List<Track> t = trackRepository.findAll();
        List<Track> newTracks = new ArrayList<Track>();
        for(int j = 0; j < a.getTracks().size(); j++) {
            for (int i = 0; i < t.size(); i++) {
                if (a.getTracks().get(j).getName().equals(t.get(i).getName())) {
                    newTracks.add(t.get(i));
                }
            }
        }
        return newTracks;
    }

    public Album findAlbumByAlbumName(String name){
        return albumRepository.findAlbumByAlbum(name);
    }
    public TrackList findTrackListByPlayListId(String playListId){
        return trackListRepository.findTrackListByPlaylistId(playListId);
    }

    public Track findTrackById(Integer id){
        return trackRepository.findTrackById(id);
    }
    public Track findTrackByTrackId(String id){
        return trackRepository.findTrackByTrackId(id);
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

    public Track saveTrack(Track track){
        return trackRepository.save(track);
    }

    public Integer deleteTrackById(Integer id){
        return trackRepository.deleteTrackById(id);
    }

    public void saveTracksAndLists(TrackList tl, Track t){
        List<TrackList> trackList = new ArrayList<TrackList>();
        List<Track> tracks = new ArrayList<Track>();
        trackList.add(tl);
        tracks.add(t);
        t.setTrackLists(trackList);
        tl.setTracks(tracks);
        trackListRepository.save(tl);
        trackRepository.save(t);
    }
}
