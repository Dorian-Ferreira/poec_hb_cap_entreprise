package fr.dorian_ferreira.cap_entreprise.dto;

import fr.dorian_ferreira.cap_entreprise.entity.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameDTO {

    @NotBlank(message = "Le jeu doit avoir un nom")
    private String name;

    @NotBlank(message = "Le jeu doit avoir une desciption")
    private String description;

    @NotNull(message = "Le jeu doit avoir une date de sortie")
    private String publishedAt;

    private String image;

    @NotNull(message = "Le jeu doit avoir un éditeur")
    private Publisher publisher;

    @NotNull(message = "Le jeu doit avoir une classification")
    private Classification classification;

    @NotNull(message = "Le jeu doit avoir un genre")
    private Genre genre;

    @NotNull(message = "Le jeu doit avoir un modélé économique")
    private BusinessModel businessModel;

    @NotNull(message = "Le jeu doit avoir au moins une platforme")
    private List<Platform> platforms = new ArrayList<>();
}