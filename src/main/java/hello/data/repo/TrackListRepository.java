package hello.data.repo;

import hello.data.domain.TrackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackListRepository extends JpaRepository<TrackList, Long> {
    TrackList findById(Long id);
    List<TrackList> findAll();
    TrackList findTrackListByPlaylistId(String listId);
    TrackList findTrackListByPlaylistIdAndLibraryPersistanceId(String listId, String libraryId);
    TrackList findTrackListByPlaylistName(String name);
}
