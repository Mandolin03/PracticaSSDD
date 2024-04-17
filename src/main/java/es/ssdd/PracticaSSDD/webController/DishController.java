package es.ssdd.PracticaSSDD.webController;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import es.ssdd.PracticaSSDD.service.DishService;

@Controller
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    RestaurantService restaurantService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/dishes")
    public String listDishes(Model model) {
        model.addAttribute("dishes", dishService.getDishes());
        return "dishes/dishes";
    }

    @GetMapping("/new-dish")
    public String showFormNewDish(Model model) {
        model.addAttribute("options", restaurantService.getRestaurants());
        return "dishes/new-dish";
    }

    @PostMapping("/new-dish")
    public String processFormNewDish(Dish dish) {
        dishService.createDish(dish);
        return "redirect:/dishes";
    }


    @GetMapping("/dishes/details/{id}")
    public String detailedDish(@PathVariable Long id, Model model) {
        Dish dish = dishService.getDish(id);
        if (dish != null){

            model.addAttribute("dish",dish);
            model.addAttribute("ingredients", dish.getIngredients());
            return "dishes/dish";
        } else {
            return "redirect:/dishes";
        }
    }

    @GetMapping("/dishes/edit/{id}")
    public String editDishForm(@PathVariable Long id, Model model) {
        if (dishService.getDish(id) != null){
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
