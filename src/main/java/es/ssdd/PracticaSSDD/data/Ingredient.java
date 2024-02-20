package es.ssdd.PracticaSSDD.data;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private Long id;
    private String name;
    private String category;

    public ArrayList<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(ArrayList<Allergen> allergens) {
        this.allergens = allergens;
    }

    private ArrayList<Allergen> allergens;



    public Ingredient() {
    }

    public Ingredient(Long id, String name, String category, List<Allergen> allergens) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.allergens = new ArrayList<>(allergens);
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
}
