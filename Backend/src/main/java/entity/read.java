package entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class read {

    @Id
    @Column(nullable = false, unique = true)
    private int readId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @Column(nullable = false, unique = true)
    private Date startDate;

    private Date endDate;

    private int rating;
}
