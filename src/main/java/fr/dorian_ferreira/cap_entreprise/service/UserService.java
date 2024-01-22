package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.ReviewDTO;
import fr.dorian_ferreira.cap_entreprise.dto.UserDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Player;
import fr.dorian_ferreira.cap_entreprise.entity.User;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.ReviewRepository;
import fr.dorian_ferreira.cap_entreprise.repository.UserRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements DAOServiceInterface<User> {

    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User getObjectById(Long id) {
        Optional<User> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("User", "id", id);
        }
        return optional.get();
    }

    public User persist(UserDTO dto, Long id) {
        if (id != null && repository.findById(id).isEmpty()) {
            throw new NotFoundEntityException(
                    "User", "id", id
            );
        }

        User entity = new Player();
        entity.setId(id);
        return repository.saveAndFlush(entity);
    }

    public UserDTO getDTOById(Long id) {
        User entity = getObjectById(id);
        UserDTO dto = new UserDTO();
        return dto;
    }
}
