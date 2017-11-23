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
               // return null;
            } else {
                userT.add(t.get(i));
            }
        }
        return userT;
    }

    public List<TrackList> displayAllLists(User u) {
        List<TrackList> t = trackListRepository.findAll();
        List<TrackList> newLists = new ArrayList<TrackList>();
        for (int j = 0; j < u.getTrackLists().size(); j++) {
            for(int i = 0; i < t.size(); i++) {
                if (u.getTrackLists().get(j).equals(t.get(i))) {
                    newLists.add(t.get(i));
                }
            }
        }
        return newLists;
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

    public List<Track> displayAllTracksForList(User u, TrackList tl) {
        String id = u.getLibraryPersistanceId();
        List<Track> newTracks = new ArrayList<Track>();
        for (int j = 0; j < tl.getTracks().size(); j++) {
            if(tl.getLibraryPersistanceId().equals(id)) {
                Track track = tl.getTracks().get(j);
                newTracks.add(track);
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

    public TrackList findTrackListById(Long id){
        return trackListRepository.findTrackListById(id);
    }

    public Track findTrackByTrackId(String id){
        return trackRepository.findTrackByTrackId(id);
    }

    public Track findTrack(String trackId, String libraryPersistnceId){
        return trackRepository.findTrackByTrackIdAndLibraryPersistanceId(trackId, libraryPersistnceId);
    }
    public TrackList findTrackList(String listId, String libraryPersistnceId){
        return trackListRepository.findTrackListByPlaylistIdAndLibraryPersistanceId(listId, libraryPersistnceId);
    }


    public TrackList findTrackListByPlaylistName(String name){
        return trackListRepository.findTrackListByPlaylistName(name);
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

    public void moveTrackTo(TrackList old, Track track, TrackList trackList){
        trackList.getTracks().add(track);
        old.getTracks().remove(track);
        track.getTrackLists().remove(old);
        trackRepository.save(track);
        trackListRepository.save(old);
        trackListRepository.save(trackList);
    }
    public Integer deleteTrackById(Integer id){
        return trackRepository.deleteTrackById(id);
    }

    public void addTracksToList(TrackList tl, Track t){
        ArrayList<Track> tracks = new ArrayList<Track>();
        tracks.add(t);
        if(tl.getTracks() == null){
            tl.setTracks(tracks);
        }else{
            tl.getTracks().add(t);
        }
        ArrayList<TrackList> tracklists = new ArrayList<TrackList>();
        tracklists.add(tl);
        if(t.getTrackLists() == null){
            t.setTrackLists(tracklists);
        }else{
            t.getTrackLists().add(tl);
        }
        trackRepository.save(t);
        trackListRepository.save(tl);
    }
}
