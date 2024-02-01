package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Publisher;
import fr.dorian_ferreira.cap_entreprise.entity.User;
import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNameRepository;
import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository
        extends JpaRepository<Publisher, Long>,
        EntityNomenclatureRepository<Publisher>,
        EntityNameRepository<Publisher>
{
    List<Publisher> findAllByOrderByName();
}
