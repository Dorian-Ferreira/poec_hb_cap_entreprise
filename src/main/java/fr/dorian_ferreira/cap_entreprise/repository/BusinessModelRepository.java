package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.BusinessModel;
import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessModelRepository
        extends JpaRepository<BusinessModel, Long>,
        EntityNomenclatureRepository<BusinessModel>
{
    List<BusinessModel> findAllByOrderByName();
}
