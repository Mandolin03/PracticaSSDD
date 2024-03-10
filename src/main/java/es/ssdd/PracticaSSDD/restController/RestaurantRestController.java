package es.ssdd.PracticaSSDD.restController;


import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<Collection<Restaurant>> getRestaurants() {
        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurant);
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant table) {
        return ResponseEntity.status(201).body(restaurantService.createTable(table));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> editRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Restaurant original = restaurantService.putRestaurant(id, restaurant);
        if (original == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(original);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant){
        if(restaurantService.editRestaurant(id, restaurant) == null)return ResponseEntity.notFound().build();
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
