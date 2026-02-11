package sabatinoprovenza.BE_S6_L3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "blogs")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Blog {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String categoria;
    @Column(nullable = false)
    private String titolo;
    private String cover;
    @Column(nullable = false)
    private String contenuto;
    private int tempoDiLettura;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Blog(String categoria, String titolo, String contenuto, int tempoDiLettura, Author author) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = "https://picsum.photos/200/300";
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.author = author;
    }
}
