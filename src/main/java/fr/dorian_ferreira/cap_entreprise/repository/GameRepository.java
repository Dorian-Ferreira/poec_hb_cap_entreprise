package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository
        extends JpaRepository<Game, Long>
{
    List<Game> findAllByOrderByName();

    Page<Game> findAllByNameContainingIgnoreCaseOrPublisherNameContainingIgnoreCaseOrGenreNameContainingIgnoreCaseOrPlatformsNameContainingIgnoreCase(String search1, String search2, String search3, String search4, Pageable pageable);

    Optional<Game> findBySlug(String slug);
}
