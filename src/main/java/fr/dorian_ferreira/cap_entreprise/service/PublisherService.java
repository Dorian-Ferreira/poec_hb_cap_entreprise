package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.PublisherDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Publisher;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.PublisherRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherService implements DAOServiceInterface<Publisher> {

    private PublisherRepository repository;

    @Override
    public List<Publisher> findAll() {
        return repository.findAll();
    }

    @Override
    public Publisher getObjectById(Long id) {
        Optional<Publisher> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("Publisher", "id", id);
        }
        return optional.get();
    }

    public Publisher persist(PublisherDTO dto, Long id) {
        if (id != null && repository.findById(id).isEmpty()) {
            throw new NotFoundEntityException(
                    "Publisher", "id", id
            );
        }

        Publisher entity = new Publisher();
        entity.setId(id);
        entity.setName(dto.getName());
        return repository.saveAndFlush(entity);
    }

    public Optional<Publisher> findByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return repository.findById(id);
        } catch (NumberFormatException e) {
            return repository.findByName(field);
        }
    }

    public PublisherDTO getDTOById(Long id) {
        Publisher entity = getObjectById(id);
        PublisherDTO dto = new PublisherDTO();
        dto.setName(entity.getName());
        return dto;
    }
}
