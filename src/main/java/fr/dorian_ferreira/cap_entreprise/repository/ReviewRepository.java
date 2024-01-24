package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Gamer;
import fr.dorian_ferreira.cap_entreprise.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface ReviewRepository
        extends JpaRepository<Review, Long>
{
    List<Review> findAllByModeratorIsNotNullOrWriter(Gamer writer);

    List<Review> findAllByGameId(Long id);
}
