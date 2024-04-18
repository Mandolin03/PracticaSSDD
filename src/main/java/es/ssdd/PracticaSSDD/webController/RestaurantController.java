package es.ssdd.PracticaSSDD.webController;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.service.DishService;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private DishService dishService;

    @GetMapping("/restaurants")
    public String getRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getRestaurants());
        return "restaurants/restaurants";
    }

    @GetMapping("/new-restaurant")
    public String showFormNewRestaurant() {
        return "restaurants/new-restaurant";
    }

    @PostMapping("/new-restaurant")
    public String processFormNewRestaurant(Restaurant restaurant) {
        restaurantService.createTable(restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants/details/{id}")
    public String detailedRestaurant(@PathVariable Long id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if (restaurant != null) {
            model.addAttribute("restaurant", restaurant);
            model.addAttribute("dishes", restaurant.getDishes());
            return "restaurants/restaurant";
        } else {
            return "redirect:/restaurants";
        }

    }

    @GetMapping("/restaurants/edit/{id}")
    public String editRestaurantForm(@PathVariable Long id, Model model) {
        if (restaurantService.getRestaurant(id) != null) {
            model.addAttribute("restaurant", restaurantService.getRestaurant(id));
            model.addAttribute("options", dishService.getDishes());
            return "restaurants/edit-restaurant";
        } else {
            return "redirect:/restaurants";
        }

    }

    @PostMapping("/restaurants/edit/{id}")
    public String editRestaurant(Restaurant restaurant) {
        restaurantService.editRestaurant(restaurant.getId(), restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants/delete/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
        restaurantService.removeRestaurant(id);
        return "redirect:/restaurants";
    }
}
