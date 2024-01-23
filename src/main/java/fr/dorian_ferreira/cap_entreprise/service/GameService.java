package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.GameDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Game;
import fr.dorian_ferreira.cap_entreprise.entity.Moderator;
import fr.dorian_ferreira.cap_entreprise.entity.Review;
import fr.dorian_ferreira.cap_entreprise.entity.User;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.GameRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService implements DAOServiceInterface<Game> {

    private GameRepository repository;
    private UserService userService;

    @Override
    public List<Game> findAll() {
        return repository.findAll();
    }

    public List<Game> findAllAvailable(User user) {
        if(user instanceof Moderator) {
            return findAll();
        }
        return repository.findAllByModeratorIsNotNull();
    }

    @Override
    public Game getObjectById(Long id) {
        Optional<Game> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("Game", "id", id);
        }
        return optional.get();
    }

    public Game persist(GameDTO dto, Long id, Principal principal) {
        if (id != null && repository.findById(id).isEmpty()) {
            throw new NotFoundEntityException(
                    "Game", "id", id
            );
        }

        Game entity = new Game();
        entity.setId(id);

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPublishedAt(dto.getPublishedAt());
        entity.setImage(dto.getImage());
        entity.setPublisher(dto.getPublisher());
        entity.setGenre(dto.getGenre());
        entity.setPlatforms(dto.getPlatforms());
        entity.setClassification(dto.getClassification());
        entity.setBusinessModel(dto.getBusinessModel());
        entity.setModerator(userService.getModeratorByName(principal.getName()));

        return repository.saveAndFlush(entity);
    }

    public GameDTO getDTOById(Long id) {
        Game entity = getObjectById(id);
        GameDTO dto = new GameDTO();

        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPublishedAt(entity.getPublishedAt());
        dto.setImage(entity.getImage());
        dto.setPublisher(entity.getPublisher());
        dto.setGenre(entity.getGenre());
        dto.setPlatforms(entity.getPlatforms());
        dto.setClassification(entity.getClassification());
        dto.setBusinessModel(entity.getBusinessModel());

        return dto;
    }
}
