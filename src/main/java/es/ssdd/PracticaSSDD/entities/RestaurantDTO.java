package es.ssdd.PracticaSSDD.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {
    private Long id;
    private String name;
    private String style;
    private Integer quality;
    private String location;
    private Long[] dishes;

    public RestaurantDTO() {
    }

    public RestaurantDTO(Long id, String name, String style, Integer quality, String location, Long[] dishes) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.quality = quality;
        this.location = location;
        this.dishes = dishes;

    }
}
