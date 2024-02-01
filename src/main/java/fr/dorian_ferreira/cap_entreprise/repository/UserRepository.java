package fr.dorian_ferreira.cap_entreprise.repository;

import fr.dorian_ferreira.cap_entreprise.entity.User;
import fr.dorian_ferreira.cap_entreprise.repository.interfaces.EntityNameRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long>,
        EntityNameRepository<User>
{
    Optional<User> findByNickname(String nickname);

    @Override
    @Query("Select u from User u where u.nickname = ?1")
    Optional<User> findByName(String nickname);
}
