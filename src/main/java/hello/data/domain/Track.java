package hello.data.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Track {
    private @Id @GeneratedValue Integer id;
    private String trackId;
    private String name;

    public Track() { }

    public Track(String trackId, String name) {
        super();
        this.trackId = trackId;
        this.name= name;
    }
}