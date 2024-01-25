package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Platform;
import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformRepository
        extends JpaRepository<Platform, Long>,
        EntityNomenclatureRepository<Platform>
{
    List<Platform> findAllByOrderByName();
}
