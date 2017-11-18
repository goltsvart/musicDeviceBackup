package hello.data.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Track {
    private @Id @GeneratedValue Long id;
    private Long trackId;
    private String name;

    public Track() { }

    public Track(Long trackId, String name) {
        super();
        this.trackId = trackId;
        this.name= name;
    }
}