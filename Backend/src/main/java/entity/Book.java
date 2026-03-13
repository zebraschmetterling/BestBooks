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
public class Book {

    @Id
    private int isbn;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String author;

    @OneToMany(mappedBy = "book")
    private List<read> reads = new ArrayList<>();

    public Book(int isbn) {
        this.isbn = isbn;
    }
}
