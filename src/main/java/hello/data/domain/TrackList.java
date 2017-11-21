package hello.data.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class TrackList {
    private @Id @GeneratedValue Long id;
    private String playlistId;
    private String playlistName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Track> tracks;

    public TrackList(){}

    public TrackList(String playlistId, String playlistName){
        this.playlistId = playlistId;
        this.playlistName= playlistName;
    }
    public TrackList(String playlistId, String playlistName, List<Track> tracks){
        this.playlistId = playlistId;
        this.playlistName= playlistName;
        this.tracks = tracks;
    }
}
