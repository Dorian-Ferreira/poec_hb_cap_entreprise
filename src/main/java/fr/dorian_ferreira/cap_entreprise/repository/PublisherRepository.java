package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Publisher;
import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNameRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository
        extends JpaRepository<Publisher, Long>,
        EntityNameRepository<Publisher>
{
}
