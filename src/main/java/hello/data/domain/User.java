package hello.data.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
public class User {
    private @Id @GeneratedValue Long id;
    private String username;
    private String password;
    @ManyToMany
    private Set<Role> roles;
    public User(){}

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

}
