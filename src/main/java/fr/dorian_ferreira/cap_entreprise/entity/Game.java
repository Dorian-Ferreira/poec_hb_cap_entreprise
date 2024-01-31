package fr.dorian_ferreira.cap_entreprise.entity;

import fr.dorian_ferreira.cap_entreprise.entity.interfaces.NomenclatureInterface;
import fr.dorian_ferreira.cap_entreprise.entity.interfaces.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Game implements NomenclatureInterface, SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String slug;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate publishedAt;

    private String image;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Classification classification;

    @ManyToMany
    @JoinTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id"), // Nom de la colonne de la table courante
            inverseJoinColumns = @JoinColumn(name = "platform_id") // Nom de la colonne de l'entité en relation
    )
    private List<Platform> platforms = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private BusinessModel businessModel;

    @OneToMany(mappedBy = "game", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private Moderator moderator;

    public void addPlatform(Platform platform) {
        platforms.add(platform);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setGame(this);
    }


    @Override
    public String getField() {
        return name;
    }
}
