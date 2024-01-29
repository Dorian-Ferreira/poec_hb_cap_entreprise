package fr.dorian_ferreira.cap_entreprise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "Text")
    private String description;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Float rating;

    @UpdateTimestamp
    private LocalDateTime moderatedAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Gamer writer;

    @ManyToOne
    private Moderator moderator;


}
