package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.ReviewDTO;
import fr.dorian_ferreira.cap_entreprise.dto.ReviewGameDTO;
import fr.dorian_ferreira.cap_entreprise.entity.*;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.ReviewRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService implements DAOServiceInterface<Review> {

    private ReviewRepository repository;

    private UserService userService;

    @Override
    public List<Review> findAll() {
        return repository.findAll();
    }
    public Page<Review> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Review> findAllFiltered(String search1, String search2, Principal principal, Pageable page, String moderation){
        if(search1==null){
            search1="";
            search2="";
        }
        if (moderation == null || moderation.equals("1")) {
            if(userService.isAdmin(principal)){
                return repository.findAllForModerator(search1, search2, search2, page);
            }
            return repository.findAllByGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase
                    (search1, search2, principal.getName(), search2, page);
        }
        if (moderation != null && moderation.equals("2")) {
            return repository.findAllByModeratorNullAndGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase
                    (search1, search2, search2, page);
        }
        if (moderation != null && moderation.equals("3")) {
            return repository.findAllByModeratorNotNullAndGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase
                    (search1, search2, search2, page);
        }

        return null;
    }

    public List<Review> find6HighRateReview() {
        return repository.findTop6ByModeratorIsNotNullOrderByRatingDesc();
    }

    public List<Review> findAllByGameId(Long id) {
        return repository.findAllByGameId(id);
    }

    @Override
    public Review findById(Long id) {
        Optional<Review> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("Review", "id", id);
        }
        return optional.get();
    }

    public Review persist(ReviewGameDTO dto, Game game, Principal principal) {
        Review entity = new Review();

        if(dto instanceof ReviewDTO r) {
            entity.setGame(r.getGame());
        } else {
            entity.setGame(game);
        }
        entity.setDescription(dto.getDescription());
        entity.setRating(dto.getRating());

        entity.setWriter(userService.getObjectByName(principal.getName()));

        return repository.saveAndFlush(entity);
    }

    public void validate(Long id, Principal principal) {
        Review r = findById(id);
        r.setModerator(userService.getModeratorByName(principal.getName()));
        repository.saveAndFlush(r);
    }

    public void refuse(Long id) {
        Review r = findById(id);
        repository.delete(r);
    }

    public Page<Review> findByGame(Game game, Pageable pageable) {
        return repository.findAllByGameAndModeratorIsNotNull(game, pageable);
    }
}
