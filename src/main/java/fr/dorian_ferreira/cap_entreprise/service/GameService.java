package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.GameDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Game;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.GameRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class GameService implements DAOServiceInterface<Game> {

    private GameRepository repository;
    private UserService userService;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public List<Game> findAll() {
        return repository.findAllByOrderByName();
    }

    public Page<Game> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Game> findAll(Pageable pageable, String search) {
        if(search == null || search.isEmpty()) {
            return findAll(pageable);
        }
        Page<Game> games = repository.findAllByNameIsContainingIgnoreCaseOrPublisherSlugIsContainingIgnoreCaseOrGenreSlugIsContainingIgnoreCaseOrBusinessModelSlugIsContainingIgnoreCaseOrClassificationSlugIsContainingIgnoreCase
                (search, search, search, search, search, pageable);
        return games;
    }

    @Override
    public Game findById(Long id) {
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
        if(id != null) {
            entity = repository.findById(id).get();
        }

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPublishedAt(LocalDate.parse(dto.getPublishedAt()));
        entity.setPublisher(dto.getPublisher());
        entity.setGenre(dto.getGenre());
        entity.setPlatforms(dto.getPlatforms());
        entity.setClassification(dto.getClassification());
        entity.setBusinessModel(dto.getBusinessModel());
        entity.setModerator(userService.getModeratorByName(principal.getName()));

        return repository.saveAndFlush(entity);
    }

    public GameDTO getDTOById(Long id) {
        Game entity = findById(id);
        GameDTO dto = new GameDTO();

        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPublishedAt(String.valueOf(entity.getPublishedAt()));
        dto.setPublisher(entity.getPublisher());
        dto.setGenre(entity.getGenre());
        dto.setPlatforms(entity.getPlatforms());
        dto.setClassification(entity.getClassification());
        dto.setBusinessModel(entity.getBusinessModel());

        return dto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void addImage(Long id, String imagePath) {
        Game game = findById(id);
        game.setImage(imagePath);

        repository.saveAndFlush(game);
    }

    public List<Game> find(int number) {
        Random random = new Random();
        long max = repository.count();
        List<Long> gamesId = new ArrayList<>();
        while(gamesId.size() < number) {
            long rnd = random.nextLong(max)+1;
            if(!gamesId.contains(rnd)){
                gamesId.add(rnd);
            }
        }
        return repository.findAllById(gamesId);
    }

    public Game findBySlug(String slug) {
        Optional<Game> optional = repository.findBySlug(slug);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("Game", "slug", slug);
        }
        return optional.get();
    }

    public Game getRandom() {
        return repository.findById((new Random()).nextLong(repository.count())+1).get();
    }

    public String getAverageRating(Game game) {
        return df.format(repository.findAverageRating(game));
    }
}
