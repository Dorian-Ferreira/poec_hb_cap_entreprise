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
public class BusinessModelDTO {

    @NotBlank(message = "The name should have a value")
    private String name;

}