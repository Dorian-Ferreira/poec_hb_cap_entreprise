package fr.dorian_ferreira.cap_entreprise.repository.interfaces;

import java.util.Optional;

public interface EntityNomenclatureRepository<T> {

    Optional<T> findByName(String name);

}
