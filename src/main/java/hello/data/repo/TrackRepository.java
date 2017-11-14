package hello.data.repo;

import hello.data.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Track findByTrackId(String trackId);
}
