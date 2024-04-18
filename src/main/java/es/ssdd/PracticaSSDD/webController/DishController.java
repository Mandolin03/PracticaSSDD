package es.ssdd.PracticaSSDD.webController;

import es.ssdd.PracticaSSDD.entities.DataTransferObject;
import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.service.IngredientService;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.ssdd.PracticaSSDD.service.DishService;

import java.util.Arrays;

@Controller
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private IngredientService ingredientService;


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
    public String processFormNewDish(DataTransferObject dto) {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setCategory(dto.getCategory());
        dish.setPrice(dto.getPrice());
        Restaurant r = restaurantService.getRestaurant(dto.getRestaurant());
        dish.setRestaurant(r);

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
        if (dishService.getDish(id) != null) {
            model.addAttribute("dish", dishService.getDish(id));
            model.addAttribute("options", restaurantService.getRestaurants());
            return "dishes/edit-dish";
        } else {
            return "redirect:/dishes";
        }
    }

    @PostMapping("/dishes/edit/{id}")
    public String editDish(Dish dish) {
        dishService.editDish(dish.getId(), dish);
        return "redirect:/dishes";
    }

    @GetMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.removeDish(id);
        return "redirect:/dishes";
    }

}
