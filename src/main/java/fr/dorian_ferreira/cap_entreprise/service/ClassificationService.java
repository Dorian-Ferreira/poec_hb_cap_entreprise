package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.ClassificationDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Classification;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.ClassificationRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassificationService implements DAOServiceInterface<Classification> {

    private ClassificationRepository repository;

    @Override
    public List<Classification> findAll() {
        return repository.findAll();
    }

    @Override
    public Classification getObjectById(Long id) {
        Optional<Classification> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("Classification", "id", id);
        }
        return optional.get();
    }

    public Classification persist(ClassificationDTO dto, Long id) {
        if (id != null && repository.findById(id).isEmpty()) {
            throw new NotFoundEntityException(
                    "Classification", "id", id
            );
        }

        Classification entity = new Classification();
        entity.setId(id);
        entity.setName(dto.getName());
        return repository.saveAndFlush(entity);
    }

    public Optional<Classification> findByField(String field) {
        try {
            Long id = Long.parseLong(field);
            return repository.findById(id);
        } catch (NumberFormatException e) {
            return repository.findByName(field);
        }
    }

    public ClassificationDTO getDTOById(Long id) {
        Classification entity = getObjectById(id);
        ClassificationDTO dto = new ClassificationDTO();
        dto.setName(entity.getName());
        return dto;
    }
}
