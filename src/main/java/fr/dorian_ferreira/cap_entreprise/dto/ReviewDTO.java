package fr.dorian_ferreira.cap_entreprise.dto;

import fr.dorian_ferreira.cap_entreprise.entity.Game;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO extends ReviewGameDTO {
    @NotNull(message = "Un avis porte sur un jeu")
    private Game game;
}