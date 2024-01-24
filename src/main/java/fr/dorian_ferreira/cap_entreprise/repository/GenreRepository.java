package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Genre;
import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNomenclatureRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository
        extends JpaRepository<Genre, Long>,
        EntityNomenclatureRepository<Genre>
{
}
