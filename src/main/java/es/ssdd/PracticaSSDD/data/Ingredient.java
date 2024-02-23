package es.ssdd.PracticaSSDD.data;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private Long id;
    private String name;
    private String category;


    public Ingredient() {
    }

    public Ingredient(Long id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
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
