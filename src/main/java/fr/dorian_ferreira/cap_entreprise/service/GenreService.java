package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.GenreDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Genre;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.GenreRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreService implements DAOServiceInterface<Genre> {

    private GenreRepository repository;

    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Override
    public Genre getObjectById(Long id) {
        Optional<Genre> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("Genre", "id", id);
        }
        return optional.get();
    }

    public Genre persist(GenreDTO dto) {

        Genre entity = new Genre();
        entity.setName(dto.getName());
        return repository.saveAndFlush(entity);
    }

    public Optional<Genre> findByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return repository.findById(id);
        } catch (NumberFormatException e) {
            return repository.findByName(field);
        }
    }
}
