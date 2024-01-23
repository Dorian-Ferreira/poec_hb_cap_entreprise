package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Review;
import fr.dorian_ferreira.cap_entreprise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository
        extends JpaRepository<Review, Long>
{
    List<Review> findAllByModeratorIsNotNullOrWriter(User writer);
}
