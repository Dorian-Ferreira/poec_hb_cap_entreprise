package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.BusinessModel;
import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNameRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessModelRepository
        extends JpaRepository<BusinessModel, Long>,
        EntityNameRepository<BusinessModel>
{
}
