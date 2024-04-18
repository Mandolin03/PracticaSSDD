package es.ssdd.PracticaSSDD.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataTransferObject {
private Long id;
    private String name;
    private String category;
    private Double price;
    private Long restaurant;
    private Long[] ingredients;

    public DataTransferObject() {
        System.out.println("DTO created");
    }

    public DataTransferObject(Long id, String name, String category, Double price, Long restaurant, Long[] ingredients) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.restaurant = restaurant;
        this.ingredients = ingredients;

    }

}

