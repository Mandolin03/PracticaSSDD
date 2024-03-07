package es.ssdd.PracticaSSDD.entities;

public class Restaurant {
    private Long id;
    private String name;
    private String style;
    private int quality;
    private String location;

    public Restaurant(){

    }

    public Restaurant(Long id, String name, String cook, int quality, String location) {
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

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
