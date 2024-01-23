package fr.dorian_ferreira.cap_entreprise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDate publishedAt;

    private String image;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Publisher publisher;

    @ManyToOne
    private Classification classification;

    @ManyToMany
    @JoinTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id"), // Nom de la colonne de la table courante
            inverseJoinColumns = @JoinColumn(name = "platform_id") // Nom de la colonne de l'entité en relation
    )
    private List<Platform> platforms = new ArrayList<>();

    @ManyToOne
    private BusinessModel businessModel;

    @OneToMany(mappedBy = "game")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    private Moderator moderator;

    public void addPlatform(Platform platform) {
        platforms.add(platform);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setGame(this);
    }

    public float getAverageRating() {
        float average = 0;
        for (Review review : reviews) {
            if(review.getModerator() != null){
                average += review.getRating();
            }
        }
        return average;
    }
}
