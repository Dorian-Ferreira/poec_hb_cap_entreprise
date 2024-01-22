package fr.dorian_ferreira.cap_entreprise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "The username should have a value")
    private String username;

    @NotBlank(message = "The password should have a value")
    private String password;

    @Email
    @NotBlank(message = "The email should have a value")
    private String email;

    @NotBlank(message = "The date should have a value")
    private LocalDate birthAt;

}