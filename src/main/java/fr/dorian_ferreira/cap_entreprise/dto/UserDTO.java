package fr.dorian_ferreira.cap_entreprise.dto;

import fr.dorian_ferreira.cap_entreprise.repository.UserRepository;
import fr.dorian_ferreira.cap_entreprise.validator.annotation.UniqueName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "Le nom d'utilisateur ne doit pas être vide")
    @UniqueName(repositoryClass = UserRepository.class)
    private String username;

    @NotBlank(message = "Il te faut un mot de passe")
    private String password;

    @Email
    @NotBlank(message = "Il te faut un email")
    private String email;

    @NotNull(message = "Il faut renseigner ta date de naissance")
    private LocalDate birthAt;

}