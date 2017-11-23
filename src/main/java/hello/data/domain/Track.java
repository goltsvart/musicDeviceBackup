package hello.data.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Track {
    private @Id @GeneratedValue Integer id;
    private String trackId;
    private String name;
    private String libraryPersistanceId;

    @ManyToMany
    private List<TrackList> trackLists;

    public Track() { }

    public Track(String trackId, String name, String libraryPersistanceId) {
        super();
        this.trackId = trackId;
        this.name= name;
        this.libraryPersistanceId = libraryPersistanceId;
    }
    public Track(String trackId, String name, String libraryPersistanceId, List<TrackList> trackLists) {
        super();
        this.trackId = trackId;
        this.name= name;
        this.libraryPersistanceId = libraryPersistanceId;
        this.trackLists = trackLists;
    }
}