package es.ssdd.PracticaSSDD;

import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.HashSet;

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
        Ingredient cebolla = new Ingredient("Cebolla");
        Ingredient pollo = new Ingredient("Pollo");
        ingredientRepository.saveAll(Arrays.asList(tomate, cebolla, pollo));

        Restaurant restaurant = new Restaurant("Restaurante SD");
        restaurantRepository.save(restaurant);

        Dish plato1 = new Dish("Pollo con cebolla", "Andaluz", 6.4, restaurant, new HashSet<>(Arrays.asList(cebolla, pollo)));
        Dish plato2 = new Dish("Tomate frito", "Valenciano", 5.5, restaurant, new HashSet<>(Arrays.asList(tomate, cebolla)));

        dishRepository.saveAll(Arrays.asList(plato1, plato2));
    }
}
