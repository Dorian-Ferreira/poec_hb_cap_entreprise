package fr.dorian_ferreira.cap_entreprise.service.interfaces;

import java.util.List;

public interface DAOServiceInterface<T> {

    List<T> findAll();

    T getObjectById(Long id);

}
