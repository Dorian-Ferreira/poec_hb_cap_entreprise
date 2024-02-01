package fr.dorian_ferreira.cap_entreprise.dto;

import fr.dorian_ferreira.cap_entreprise.repository.PublisherRepository;
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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublisherDTO {

    @NotBlank(message = "Le nom ne doit pas être vide")
    @UniqueName(repositoryClass = PublisherRepository.class)
    private String name;

}