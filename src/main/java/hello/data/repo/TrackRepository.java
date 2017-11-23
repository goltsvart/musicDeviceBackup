package hello.data.repo;

import hello.data.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Integer> {
    List<Track> findAll();
    Track findTrackByTrackId(String trackId);
    Track findTrackByTrackIdAndLibraryPersistanceId(String trackId, String libraryPersistnceId);
    Track findTrackById(Integer id);
    @Transactional
    Integer deleteTrackById(Integer id);
}