package es.ssdd.PracticaSSDD.restController;


import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.entities.RestaurantDTO;
import es.ssdd.PracticaSSDD.service.DishService;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.MalformedParametersException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @GetMapping
    public ResponseEntity<Collection<RestaurantDTO>> getRestaurants() {
        List<RestaurantDTO> restaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantService.getRestaurants()) {
            RestaurantDTO dto = new RestaurantDTO();
            dto.setId(restaurant.getId());
            dto.setName(restaurant.getName());
            dto.setStyle(restaurant.getStyle());
            dto.setQuality(restaurant.getQuality());
            dto.setLocation(restaurant.getLocation());
            List<Long> dishes = new ArrayList<>();
            for (Dish d : restaurant.getDishes()) {
                dishes.add(d.getId());
            }
            dto.setDishes(dishes.toArray(new Long[0]));
            restaurants.add(dto);
        }
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }
        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setStyle(restaurant.getStyle());
        dto.setQuality(restaurant.getQuality());
        dto.setLocation(restaurant.getLocation());
        List<Long> dishes = new ArrayList<>();
        for (Dish d : restaurant.getDishes()) {
            dishes.add(d.getId());
        }
        dto.setDishes(dishes.toArray(new Long[0]));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO dto) {
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(dto.getName());
            restaurant.setStyle(dto.getStyle());
            restaurant.setQuality(dto.getQuality());
            restaurant.setLocation(dto.getLocation());
            if(dto.getDishes() == null) throw new MalformedParametersException();
            List<Dish> dishes = new ArrayList<>();
            for (Long d : dto.getDishes()) {
                Dish dish = dishService.getDish(d);
                if (dish.getRestaurant() != null) throw new MalformedParametersException();
                dish.setRestaurant(restaurant);
                dishes.add(dish);
            }
            restaurant.setDishes(new HashSet<>(dishes));
            restaurantService.createRestaurant(restaurant);
            dto.setId(restaurant.getId());
            return ResponseEntity.status(201).body(dto);
        } catch (MalformedParametersException | EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> editRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO dto) {
        Restaurant original;
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(dto.getId());
            restaurant.setName(dto.getName());
            restaurant.setStyle(dto.getStyle());
            restaurant.setQuality(dto.getQuality());
            restaurant.setLocation(dto.getLocation());
            List<Dish> dishes = new ArrayList<>();
            for (Long d : dto.getDishes()) {
                Dish dish = dishService.getDish(d);
                if (dish.getRestaurant() != null && dish.getRestaurant().getId() != id)
                    throw new MalformedParametersException();
                dish.setRestaurant(restaurant);
                dishes.add(dish);
            }
            restaurant.setDishes(new HashSet<>(dishes));
            dto.setId(id);
            original = restaurantService.putRestaurant(id, restaurant);
        } catch (MalformedParametersException | NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
        if (original == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO dto) {
        Restaurant edited;
        try {
            if(restaurantService.getRestaurant(id) == null) return ResponseEntity.notFound().build();
            Restaurant r = new Restaurant();
            r.setId(id);
            r.setName(dto.getName());
            r.setStyle(dto.getStyle());
            r.setQuality(dto.getQuality());
            r.setLocation(dto.getLocation());
            List<Dish> dishes = new ArrayList<>();
            if(dto.getDishes() != null){
                for (Long d : dto.getDishes()) {
                    Dish dish = dishService.getDish(d);
                    if (dish.getRestaurant() != null && dish.getRestaurant().getId() != id)
                        throw new MalformedParametersException();
                    dish.setRestaurant(r);
                    dishes.add(dish);
                }
            }

            r.setDishes(new HashSet<>(dishes));
            edited = restaurantService.editRestaurant(id, r);
        } catch (MalformedParametersException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        if (edited == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeRestaurant(@PathVariable Long id) {
        if (restaurantService.removeRestaurant(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}