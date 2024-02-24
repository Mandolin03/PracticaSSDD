package es.ssdd.PracticaSSDD.data;

import java.util.ArrayList;

public class Dish {
    private Long id;
    private String name;
    private String category;
    private Double price;

    public Dish() {
    }

    public Dish(Long id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String formattedPrice(){
        return "%.2f".formatted(price);
    }
}