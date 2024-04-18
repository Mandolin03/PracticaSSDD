package es.ssdd.PracticaSSDD.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientDataTransferObject {
    private Long id;
    private String name;
    private String category;
    private String origin;
    private Long[] dishes;

    public IngredientDataTransferObject() {
        System.out.println("DTO created");
    }

    public IngredientDataTransferObject(Long id, String name, String category, String origin, Long[] dishes) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.origin = origin;
        this.dishes = dishes;

    }

}
