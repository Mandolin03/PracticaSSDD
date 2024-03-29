package es.ssdd.PracticaSSDD.entities;

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
