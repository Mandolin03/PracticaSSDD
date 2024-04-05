package es.ssdd.PracticaSSDD;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Restaurant;
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
    private DishRepository dishRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public void run(String... args) throws Exception {
        Ingredient tomate = new Ingredient("Tomate");
        Ingredient arroz = new Ingredient("Arroz");
        Ingredient pollo = new Ingredient("Pollo");
        ingredientRepository.saveAll(Arrays.asList(tomate, arroz, pollo));

    }
}
