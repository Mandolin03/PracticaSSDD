package es.ssdd.PracticaSSDD.restController;


import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public ResponseEntity<Collection<Restaurant>> getRestaurants() {
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurant);
    }

    @PostMapping("/new-restaurant")
    public ResponseEntity<Restaurant> createTable(@RequestBody Restaurant table) {
        return ResponseEntity.status(201).body(restaurantService.createTable(table));
    }

    @PutMapping("/edit-restaurant/{id}")
    public ResponseEntity<Restaurant> editTable(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Restaurant original = restaurantService.editRestaurant(id, restaurant);
        if (original == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(original);
    }

    @DeleteMapping("/delete-restaurant/{id}")
    public ResponseEntity<Void> removeRestaurant(@PathVariable Long id) {
        if (restaurantService.removeRestaurant(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
