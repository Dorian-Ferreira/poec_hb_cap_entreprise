package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Classification;
import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNameRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationRepository
        extends JpaRepository<Classification, Long>,
        EntityNameRepository<Classification>
{
}