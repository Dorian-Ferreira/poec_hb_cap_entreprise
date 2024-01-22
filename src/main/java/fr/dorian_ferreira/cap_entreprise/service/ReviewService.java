package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.GameDTO;
import fr.dorian_ferreira.cap_entreprise.dto.ReviewDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Game;
import fr.dorian_ferreira.cap_entreprise.entity.Review;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.GameRepository;
import fr.dorian_ferreira.cap_entreprise.repository.ReviewRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    @Override
    public Review getObjectById(Long id) {
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
}
