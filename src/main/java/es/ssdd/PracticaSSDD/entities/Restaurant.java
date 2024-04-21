package es.ssdd.PracticaSSDD.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String style;
    private Integer quality;
    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Dish> dishes = new HashSet<>();

    public Restaurant(String name){
        this.name = name;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
        dish.setRestaurant(this);
    }

}