package fr.dorian_ferreira.cap_entreprise.service;

import fr.dorian_ferreira.cap_entreprise.dto.UserDTO;
import fr.dorian_ferreira.cap_entreprise.entity.Moderator;
import fr.dorian_ferreira.cap_entreprise.entity.Gamer;
import fr.dorian_ferreira.cap_entreprise.entity.User;
import fr.dorian_ferreira.cap_entreprise.exception.NotFoundEntityException;
import fr.dorian_ferreira.cap_entreprise.repository.UserRepository;
import fr.dorian_ferreira.cap_entreprise.service.interfaces.DAOServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements DAOServiceInterface<User>, UserDetailsService {

    private UserRepository repository;

    private BCryptPasswordEncoder passwordEncoder;

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

    public User persist(UserDTO dto) {
        Gamer entity = new Gamer();

        entity.setNickname(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setEmail(dto.getEmail());
        entity.setBirthAt(dto.getBirthAt());

        return repository.saveAndFlush(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                userGrantedAuthority(user)
        );
    }

    private List<GrantedAuthority> userGrantedAuthority(User user) {
        if (user instanceof Moderator) {
            return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_GAMER"));
    }

    public Gamer getObjectByName(String name) {
        Optional<User> optional = repository.findByNickname(name);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("User", "name", name);
        }
        return (Gamer)optional.get();
    }

    public User findByName(String name) {
        Optional<User> optional = repository.findByNickname(name);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("User", "name", name);
        }
        return optional.get();
    }

    public Moderator getModeratorByName(String name) {
        Optional<User> optional = repository.findByNickname(name);
        if (optional.isEmpty()) {
            throw new NotFoundEntityException("User", "name", name);
        }
        return (Moderator)optional.get();
    }
}
