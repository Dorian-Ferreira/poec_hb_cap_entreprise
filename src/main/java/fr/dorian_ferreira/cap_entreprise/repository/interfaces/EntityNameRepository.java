package fr.dorian_ferreira.cap_entreprise.repository.interfaces;

import java.util.Optional;

public interface EntityNameRepository<T> {

    Optional<T> findByNickname(String name);

}
