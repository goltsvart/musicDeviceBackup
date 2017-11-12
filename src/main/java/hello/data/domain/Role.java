package hello.data.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
public class Role {
    private @Id @GeneratedValue Long id;
    private String name;
    @ManyToMany
    private Set<User> users;

    public Role(String name) {
        super();
        this.name = name;
    }
}