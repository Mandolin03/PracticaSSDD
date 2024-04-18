package es.ssdd.PracticaSSDD.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDataTransferObject {
    private Long id;
    private String name;
    private String style;
    private Double quality;
    private String location;
    private Long[] dishes;

    public RestaurantDataTransferObject() {
        System.out.println("DTO created");
    }

    public RestaurantDataTransferObject(Long id, String name, String style, Double quality, String location, Long[] dishes) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.quality = quality;
        this.location = location;
        this.dishes = dishes;

    }
}
