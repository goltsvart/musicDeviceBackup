package hello.data.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Album {
    private @Id
    @GeneratedValue
    Long id;
    private String libraryPersistanceId;
    private String artist;
    private String album;
    private String year;
    private String genre;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval=true)
    private List<Track> tracks;

    public Album() { }

    public Album(String libraryPersistanceId, String artist, String album, String year, String genre) {
        super();
        this.libraryPersistanceId = libraryPersistanceId;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
    }
    public Album(String libraryPersistanceId, String artist, String album, String year, String genre, List<Track> tracks) {
        super();
        this.libraryPersistanceId = libraryPersistanceId;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
        this.tracks = tracks;
    }
}
