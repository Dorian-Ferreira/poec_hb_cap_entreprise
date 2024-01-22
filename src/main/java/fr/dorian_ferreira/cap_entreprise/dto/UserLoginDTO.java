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

    @NotBlank(message = "The username should have a value")
    private String username;

    @NotBlank(message = "The password should have a value")
    private String password;
}