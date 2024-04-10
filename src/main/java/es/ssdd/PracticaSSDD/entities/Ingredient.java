package es.ssdd.PracticaSSDD.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String origin;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Dish> dishes = new HashSet<>();

    public Ingredient(String name) {
        this.name = name;
    }
}



//TODO delete commented code
/*
public class Ingredient {
    private Long id;
    private String name;
    private String category; //Fruta,verdura,carne,pesacdo...
    private String origin; //España, Portugal, Marruecos...


    public Ingredient() {
    }

    public Ingredient(Long id, String name, String category, String origin) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.origin = origin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
*/