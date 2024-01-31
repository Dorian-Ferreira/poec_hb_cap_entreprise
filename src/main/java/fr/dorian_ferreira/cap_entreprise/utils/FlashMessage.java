package fr.dorian_ferreira.cap_entreprise.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlashMessage {

    private String type;

    private String message;

}