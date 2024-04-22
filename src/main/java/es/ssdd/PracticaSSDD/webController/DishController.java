package es.ssdd.PracticaSSDD.webController;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.DishDTO;
import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.service.IngredientService;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.ssdd.PracticaSSDD.service.DishService;

import java.util.*;

@Controller
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;


    @GetMapping("/dishes")
    public String listDishes(Model model) {
        model.addAttribute("dishes", dishService.getDishes());
        return "dishes/dishes";
    }

    @GetMapping("/new-dish")
    public String showFormNewDish(Model model) {
        model.addAttribute("options", restaurantService.getRestaurants());
        model.addAttribute("ingredients", ingredientService.getIngredients());
        return "dishes/new-dish";
    }

    @PostMapping("/new-dish")
    public String processFormNewDish(DishDTO dto) {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setCategory(dto.getCategory());
        dish.setPrice(dto.getPrice());
        if(dto.getRestaurant() == -1) dish.setRestaurant(null);
        else{
            Restaurant r = restaurantService.getRestaurant(dto.getRestaurant());
            dish.setRestaurant(r);
        }

        for (Long id : dto.getIngredients()) {
            Ingredient ingredient = ingredientService.getIngredient(id);
            dish.addIngredient(ingredient);
        }
        dishService.createDish(dish);

        return "redirect:/dishes";
    }


    @GetMapping("/dishes/details/{id}")
    public String detailedDish(@PathVariable Long id, Model model) {
        Dish dish = dishService.getDish(id);
        if (dish != null) {
            model.addAttribute("dish", dish);
            model.addAttribute("ingredients", dish.getIngredients());
            return "dishes/dish";
        } else {
            return "redirect:/dishes";
        }
    }

    @GetMapping("/dishes/edit/{id}")
    public String editDishForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.getDish(id);
        if (dish != null) {
            model.addAttribute("dish", dish);
            List<Restaurant> restaurants = new ArrayList<>(restaurantService.getRestaurants());
            restaurants.remove(dish.getRestaurant());
            model.addAttribute("options", restaurants);
            List<Ingredient> noSelectedIngredients = new ArrayList<>(ingredientService.getIngredients());
            noSelectedIngredients.removeAll(dish.getIngredients());
            model.addAttribute("selected", dish.getIngredients());
            model.addAttribute("noSelected", noSelectedIngredients);
            return "dishes/edit-dish";
        } else {
            return "redirect:/dishes";
        }
    }

    @PostMapping("/dishes/edit/{id}")
    public String editDish(DishDTO dto, @PathVariable Long id) {
        Dish dish = new Dish();
        dish.setId(id);

        dish.setName(dto.getName());
        dish.setCategory(dto.getCategory());
        dish.setPrice(dto.getPrice());

        if(dto.getRestaurant() == -1){
            dish.setRestaurant(null);
        } else{
            Restaurant r = restaurantService.getRestaurant(dto.getRestaurant());
            dish.setRestaurant(r);
        }
        dish.setIngredients(new HashSet<>());
        for (Long ingId : dto.getIngredients()) {
            Ingredient ingredient = ingredientService.getIngredient(ingId);
            dish.addIngredient(ingredient);
        }

        dishService.editDish(id, dish);
        return "redirect:/dishes";
    }

    @GetMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.removeDish(id);
        return "redirect:/dishes";
    }

}
