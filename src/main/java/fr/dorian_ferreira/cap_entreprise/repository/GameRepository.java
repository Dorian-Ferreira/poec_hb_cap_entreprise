package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository
        extends JpaRepository<Game, Long>
{

    List<Game> findTop5ByOrderById();
}
