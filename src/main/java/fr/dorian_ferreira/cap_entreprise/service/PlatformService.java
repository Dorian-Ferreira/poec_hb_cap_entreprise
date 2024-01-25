package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.PlatformDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Platform;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.PlatformRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlatformService implements DAOServiceInterface<Platform> {

    private PlatformRepository repository;

    @Override
    public List<Platform> findAll() {
        return repository.findAllByOrderByName();
    }

    @Override
    public Platform findById(Long id) {
        Optional<Platform> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("Platform", "id", id);
        }
        return optional.get();
    }

    public Platform persist(PlatformDTO platformDTO, Long id) {
        if (id != null && repository.findById(id).isEmpty()) {
            throw new NotFoundEntityException(
                    "Platform", "id", id
            );
        }

        Platform entity = new Platform();
        entity.setId(id);
        entity.setName(platformDTO.getName());
        return repository.saveAndFlush(entity);
    }
}
