package es.ssdd.PracticaSSDD;

import java.util.ArrayList;

public class Dish {
    private Long id;
    private String name;
    private String category;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Ingredient> allergens;
    private Double price;
    //TODO add image

    public Dish() {
    }

    public Dish(Long id, String name, String category, ArrayList<Ingredient> ingredients, ArrayList<Ingredient> allegens, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.allergens = allegens;
        this.price = price;
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getAllergens() {
        return allergens;
    }

    public void setAllergens(ArrayList<Ingredient> allergens) {
        this.allergens = allergens;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}