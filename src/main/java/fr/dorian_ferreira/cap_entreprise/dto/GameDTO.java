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

    @NotBlank(message = "The name should have a value")
    private String name;

    @NotBlank(message = "The description should have a value")
    private String description;

    @NotNull
    private LocalDate publishedAt;

    private String image;

    private Publisher publisher;
    private Classification classification;
    private Genre genre;
    private BusinessModel businessModel;
    private List<Platform> platforms = new ArrayList<>();
}