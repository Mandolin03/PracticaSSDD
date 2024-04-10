package es.ssdd.PracticaSSDD;

import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public void run(String... args) throws Exception {
        Ingredient tomate = new Ingredient("Tomate");
        tomate.setCategory("Fruta");
        tomate.setOrigin("España");
        Ingredient cebolla = new Ingredient("Cebolla");
        cebolla.setCategory("Fruta");
        cebolla.setOrigin("Francia");
        Ingredient pollo = new Ingredient("Pollo");
        pollo.setCategory("Carne");
        pollo.setOrigin("España");


        Restaurant restaurante1 = new Restaurant("Restaurante SD");


        Dish plato1 = new Dish("Pollo con cebolla", "Andaluz", 6.4, restaurante1, new HashSet<>(Arrays.asList(cebolla, pollo)));
        Dish plato2 = new Dish("Tomate frito", "Valenciano", 5.5, restaurante1, new HashSet<>(Arrays.asList(tomate, cebolla)));

        tomate.setDishes(new HashSet<>(List.of(plato2)));

        restaurantRepository.save(restaurante1);
        ingredientRepository.saveAll(Arrays.asList(tomate, cebolla, pollo));
        dishRepository.saveAll(Arrays.asList(plato1, plato2));
    }
}
