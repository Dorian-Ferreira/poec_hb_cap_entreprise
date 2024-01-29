package fr.dorian_ferreira.cap_entreprise.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginDTO {

    @NotBlank(message = "Le nom d'utilisateur ne doit pas être vide")
    private String username;

    @NotBlank(message = "Il te faut un mot de passe")
    private String password;
}