package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Game;
import fr.dorian_ferreira.cap_entreprise.entity.Gamer;
import fr.dorian_ferreira.cap_entreprise.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository
        extends JpaRepository<Review, Long>
{

    Page<Review> findAllByModeratorIsNotNullOrWriterOrderByModerator(Gamer writer, Pageable pageable);

    @Query("Select r From Review r Join Game g on r.game = g join User u on r.writer = u where (r.moderator is not NULL or u.nickname like ?3) AND (g.name like %?1% or u.nickname like %?2% or g.slug like %?4%)")
    Page<Review> findAllByGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase(String search1, String search2, String username, String search3, Pageable pageable);

    @Query("Select r From Review r Join Game g on r.game = g join User u on r.writer = u where g.name like %?1% or u.nickname like %?2% or g.slug like %?3%")
    Page<Review> findAllForModerator(String search1, String search2, String search3, Pageable pageable);

    @Query("Select r From Review r Join Game g on r.game = g join User u on r.writer = u where (r.moderator is not NULL OR r.writer = ?4) AND (g.name like %?1% or u.nickname like %?2% or g.slug like %?3%)")
    Page<Review> findAllByModeratorNotNullAndGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase(String search1, String search2, String search3, Gamer writer, Pageable pageable);

    @Query("Select r From Review r Join Game g on r.game = g join User u on r.writer = u where r.moderator is NULL AND (g.name like %?1% or u.nickname like %?2% or g.slug like %?3%)")
    Page<Review> findAllByModeratorNullAndGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase(String search1, String search2, String search3, Pageable pageable);

    List<Review> findTop6ByModeratorIsNotNullOrderByRatingDesc();

    List<Review> findAllByGameId(Long id);

    Page<Review> findAllByGameAndModeratorIsNotNull(Game game, Pageable pageable);
}
