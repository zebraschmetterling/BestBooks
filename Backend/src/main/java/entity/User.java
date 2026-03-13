package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private int userId;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "user")
    private List<read> books = new ArrayList<>();

    @OneToMany(mappedBy = "followed")
    private List<Friends> follower = new ArrayList<>();

    @OneToMany(mappedBy = "follower")
    private List<Friends> following = new ArrayList<>();

}
