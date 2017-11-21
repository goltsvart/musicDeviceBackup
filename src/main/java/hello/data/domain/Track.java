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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TrackList> trackLists;

    public Track() { }

    public Track(String trackId, String name) {
        super();
        this.trackId = trackId;
        this.name= name;
    }
    public Track(String trackId, String name, List<TrackList> trackLists) {
        super();
        this.trackId = trackId;
        this.name= name;
        this.trackLists = trackLists;
    }
}