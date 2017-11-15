package hello.service;

import hello.data.domain.Track;
import hello.data.domain.User;
import hello.data.repo.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackService {
    @Autowired
    private TrackRepository trackRepository;

    public List<Track> displayAll(User u) {
        List<Track> t = trackRepository.findAll();
        List<Track> userT = new ArrayList<Track>();
        for (int i = 0; i < t.size(); i++) {
            if (!u.getLibraryPersistanceId().equals(t.get(i).getLibraryPersistanceId())) {
                return null;
            } else {
                userT.add(t.get(i));
            }
        }
        return userT;
    }
}
