package hello.data.repo;

import hello.data.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findAll();
    Track findTrackByTrackId(Long trackId);
}