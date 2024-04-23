package es.ssdd.PracticaSSDD.entities;

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

    @ManyToMany(mappedBy = "ingredients", cascade=CascadeType.REMOVE)
    private Set<Dish> dishes = new HashSet<>();


    public void removeDish(Dish dish){
        Set<Dish> dishesCopy = new HashSet<>(dishes);
        dishesCopy.remove(dish);
        dish.getIngredients().remove(this);
        setDishes(dishesCopy);
    }
}


