package es.ssdd.PracticaSSDD.entities;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.MERGE})
    @JoinTable(
            name = "dish_ingredient",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

    public Dish(String name, String category, Double price, Restaurant restaurant, Set<Ingredient> ingredients) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.restaurant = restaurant;
        this.ingredients = ingredients;
    }

    public String formattedPrice(){
        return "%.2f".formatted(price);
    }

}
