package es.ssdd.PracticaSSDD.webController;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.entities.RestaurantDTO;
import es.ssdd.PracticaSSDD.service.DishService;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;


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
    public String showFormNewRestaurant(Model model) {
        Collection<Dish> dishes = dishService.getDishes();
        List<Dish> emptyDishes = new ArrayList<>();
        for(Dish d : dishes){
            if(d.getRestaurant() == null){
                emptyDishes.add(d);
            }
        }
        model.addAttribute("dishes", emptyDishes);
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
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if (restaurant != null) {
            model.addAttribute("restaurant", restaurant);

            List<Dish> noSelectedDishes = new ArrayList<>(dishService.getDishes());

            noSelectedDishes.removeIf(d -> d.getRestaurant() != null);

            model.addAttribute("selected", restaurant.getDishes());
            model.addAttribute("noSelected", noSelectedDishes);
            return "restaurants/edit-restaurant";
        }
        else return "redirect:/restaurants";
    }

    @PostMapping("/restaurants/edit/{id}")
    public String editRestaurant(RestaurantDTO dto, @PathVariable Long id) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(id);
        restaurant.setName(dto.getName());
        restaurant.setStyle(dto.getStyle());
        restaurant.setQuality(dto.getQuality());
        restaurant.setLocation(dto.getLocation());
        restaurant.setDishes(new HashSet<>());
        if(dto.getDishes() != null){
            for (Long dishId : dto.getDishes()) {
                Dish dish = dishService.getDish(dishId);
                restaurant.addDish(dish);
            }
        }
        Collection<Dish> dishes = dishService.getDishes();
        dishes.removeAll(restaurant.getDishes());
        for (Dish d : dishes){
            if (d.getRestaurant() != null && d.getRestaurant().getId() == id){
                d.setRestaurant(null);
                dishService.editDish(d.getId(), d);
            }
        }

        restaurantService.editRestaurant(restaurant.getId(), restaurant);
        return "redirect:/restaurants";
    }

    @GetMapping("/restaurants/delete/{id}")
    public String deleteRestaurant(@PathVariable Long id) {
        restaurantService.removeRestaurant(id);
        return "redirect:/restaurants";
    }
}
