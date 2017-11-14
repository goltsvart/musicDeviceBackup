package hello.data.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Track {
    private @Id Long trackId;
    private String libraryPersistanceId;
    private String name;
    private String artist;
    private String album;
    private String year;
    private String genre;

    public Track() { }

    public Track(Long trackId, String libraryPersistanceId, String name, String artist, String album, String year, String genre) {
        super();
        this.trackId = trackId;
        this.libraryPersistanceId = libraryPersistanceId;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
    }


}
