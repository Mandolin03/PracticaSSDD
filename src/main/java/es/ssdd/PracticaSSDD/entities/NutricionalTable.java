package es.ssdd.PracticaSSDD.entities;

public class NutricionalTable {
    private Long id;
    private Float calories;
    private Float protein;
    private Float carbohydrates;
    private Float fats;


    public NutricionalTable(Long id, Float calories, Float protein, Float carbohydrates, Float fats) {
        this.id = id;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
    }

    public NutricionalTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public Float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Float getFats() {
        return fats;
    }

    public void setFats(Float fats) {
        this.fats = fats;
    }
}
