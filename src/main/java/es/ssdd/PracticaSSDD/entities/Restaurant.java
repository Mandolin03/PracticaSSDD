package es.ssdd.PracticaSSDD.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String style;
    private Integer quality;
    private String location;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Dish> dishes = new HashSet<>();

    public Restaurant(String name) {
        this.name = name;
    }
}
/*
public class Restaurant {
    private Long id;
    private String name;
    private String style;
    private Integer quality;
    private String location;

    public Restaurant(){

    }

    public Restaurant(Long id, String name, String cook, Integer quality, String location) {
        this.id = id;
        this.name = name;
        this.style = cook;
        this.quality = quality;
        this.location = location;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}*/
