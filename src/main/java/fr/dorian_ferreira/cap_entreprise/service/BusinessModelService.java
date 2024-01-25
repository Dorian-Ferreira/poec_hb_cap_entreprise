package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.BusinessModelDTO;
import fr.dorian_ferreira.cap_entreprise.entity.BusinessModel;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.BusinessModelRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusinessModelService implements DAOServiceInterface<BusinessModel> {

    private BusinessModelRepository repository;

    @Override
    public List<BusinessModel> findAll() {
        return repository.findAllByOrderByName();
    }

    @Override
    public BusinessModel findById(Long id) {
        Optional<BusinessModel> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("BusinessModel", "id", id);
        }
        return optional.get();
    }

    public BusinessModel persist(BusinessModelDTO dto, Long id) {
        if (id != null && repository.findById(id).isEmpty()) {
            throw new NotFoundEntityException(
                    "BusinessModel", "id", id
            );
        }

        BusinessModel entity = new BusinessModel();
        entity.setId(id);
        entity.setName(dto.getName());
        return repository.saveAndFlush(entity);
    }
}
