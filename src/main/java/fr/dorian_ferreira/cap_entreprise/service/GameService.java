package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.GameDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Game;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.GameRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService implements DAOServiceInterface<Game> {

    private GameRepository repository;

    @Override
    public List<Game> findAll() {
        return repository.findAll();
    }

    @Override
    public Game getObjectById(Long id) {
        Optional<Game> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("Game", "id", id);
        }
        return optional.get();
    }

    public Game persist(GameDTO dto, Long id) {
        if (id != null && repository.findById(id).isEmpty()) {
            throw new NotFoundEntityException(
                    "Game", "id", id
            );
        }

        Game entity = new Game();
        entity.setId(id);
        entity.setName(dto.getName());
        return repository.saveAndFlush(entity);
    }

    public Optional<Game> findByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return repository.findById(id);
        } catch (NumberFormatException e) {
            return repository.findByName(field);
        }
    }

    public GameDTO getDTOById(Long id) {
        Game entity = getObjectById(id);
        GameDTO dto = new GameDTO();
        dto.setName(entity.getName());
        return dto;
    }
}
