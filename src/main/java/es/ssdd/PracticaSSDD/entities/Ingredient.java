package es.ssdd.PracticaSSDD.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String origin;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private Set<Dish> dishes = new HashSet<>();
}


