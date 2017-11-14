package hello.data.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class Track {
    private @Id @GeneratedValue Long id;
    private String trackId;
    private String name;
    private String artist;
    private String album;
    private String year;
    private String genre;

    public Track(String trackId, String name, String artist, String album, String year, String genre) {
        super();
        this.trackId = trackId;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.genre = genre;
    }


}
