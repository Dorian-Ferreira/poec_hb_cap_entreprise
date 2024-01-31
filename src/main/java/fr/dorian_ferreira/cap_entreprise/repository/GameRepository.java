package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository
        extends JpaRepository<Game, Long>
{
    List<Game> findAllByOrderByName();

    Page<Game> findAllByNameIsContainingIgnoreCaseOrPublisherSlugIsContainingIgnoreCaseOrGenreSlugIsContainingIgnoreCaseOrBusinessModelSlugIsContainingIgnoreCaseOrClassificationSlugIsContainingIgnoreCase
            (String search1, String search2, String search3, String search4, String search5, Pageable pageable);


    Optional<Game> findBySlug(String slug);

    @Query("SELECT AVG(r.rating) FROM Game g JOIN Review r ON r.game = g WHERE g = ?1 AND r.moderator IS NOT NULL")
    Double findAverageRating(Game game);
}
