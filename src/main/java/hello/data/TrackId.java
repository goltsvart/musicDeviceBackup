package hello.data;
import lombok.Data;

@Data
public class TrackId {
    private String trackId;
    private String playlistId;
    public TrackId(){}
    public TrackId(String trackId, String playlistId) {
        super();
        this.trackId = trackId;
        this.playlistId = playlistId;
    }
    public String getTrackId(){
        return trackId;
    }
}
