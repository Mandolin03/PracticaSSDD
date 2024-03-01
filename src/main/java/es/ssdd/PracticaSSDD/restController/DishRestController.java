package es.ssdd.PracticaSSDD.restController;

import es.ssdd.PracticaSSDD.entities.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.ssdd.PracticaSSDD.service.DishService;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class DishRestController {
    @Autowired
    private DishService dishService;

    @GetMapping("/dishes")
    public ResponseEntity<Collection<Dish>> obtainAllDishes() {
        return ResponseEntity.ok(dishService.obtainAllDishes());
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> obtainDish(@PathVariable Long id) {
        Dish dish = dishService.obtainDish(id);
        if (dish == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dish);
    }

    @PostMapping("/new-dish")
    public ResponseEntity<Dish> createDish(@RequestBody Dish dish) {
        return ResponseEntity.status(201).body(dishService.createDish(dish));
    }

    @PutMapping("/edit-dish/{id}")
    public ResponseEntity<Dish> editDish(@PathVariable Long id, @RequestBody Dish dish) {
        Dish editDish = dishService.editDish(id, dish);
        if (editDish == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editDish);
    }

    @DeleteMapping("/delete-dish/{id}")
    public ResponseEntity<Void> removeDish(@PathVariable Long id) {
        dishService.removeDish(id);
        return ResponseEntity.ok().build();
    }
}
