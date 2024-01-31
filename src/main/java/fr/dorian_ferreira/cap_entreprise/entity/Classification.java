package fr.dorian_ferreira.cap_entreprise.entity;

import fr.dorian_ferreira.cap_entreprise.entity.interfaces.NomenclatureInterface;
import fr.dorian_ferreira.cap_entreprise.entity.interfaces.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classification implements NomenclatureInterface, SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "classification")
    private List<Game> games = new ArrayList<>();

    private String slug;

    @Override
    public String getField() {
        return name;
    }
}
