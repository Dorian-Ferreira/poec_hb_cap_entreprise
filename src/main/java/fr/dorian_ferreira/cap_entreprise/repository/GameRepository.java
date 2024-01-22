package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Game;
import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNameRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository
        extends JpaRepository<Game, Long>,
        EntityNameRepository<Game>
{
}
