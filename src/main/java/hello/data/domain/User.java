package hello.data.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class User {
    private @Id @GeneratedValue Long id;
    private String username;
    private String password;
    private String libraryPersistanceId;
    @ManyToMany
    private Set<Role> roles;
    @OneToMany
    private List<Album> album;

    public User(){}

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    public User(String username, String password, String libraryPersistanceId, List<Album> album) {
        super();
        this.username = username;
        this.password = password;
        this.libraryPersistanceId = libraryPersistanceId;
        this.album = album;
    }
}
