package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Gamer;
import fr.dorian_ferreira.cap_entreprise.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository
        extends JpaRepository<Review, Long>
{

    Page<Review> findAllByModeratorIsNotNullOrWriterOrderByModerator(Gamer writer, Pageable pageable);

    List<Review> findAllByGameId(Long id);
}
