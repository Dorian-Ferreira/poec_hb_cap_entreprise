package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.ReviewDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Gamer;
import fr.dorian_ferreira.cap_entreprise.entity.Moderator;
import fr.dorian_ferreira.cap_entreprise.entity.Review;
import fr.dorian_ferreira.cap_entreprise.entity.User;
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

    public Page<Review> findAllAvailable(Pageable pageable, User user) {
        if(user instanceof Moderator) {
            return repository.findAll(pageable);
        }
        return repository.findAllByModeratorIsNotNullOrWriterOrderByModerator((Gamer)user, pageable);
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

    public Review persist(ReviewDTO dto, Principal principal) {
        Review entity = new Review();

        entity.setGame(dto.getGame());
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

}
