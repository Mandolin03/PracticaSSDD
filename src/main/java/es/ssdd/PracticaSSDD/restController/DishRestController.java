package es.ssdd.PracticaSSDD.restController;

import es.ssdd.PracticaSSDD.entities.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.ssdd.PracticaSSDD.service.DishService;

import java.util.Collection;

@RestController
@RequestMapping("/api/dishes")
public class DishRestController {
    @Autowired
    private DishService dishService;

    @GetMapping
    public ResponseEntity<Collection<Dish>> obtainAllDishes() {
        return ResponseEntity.ok(dishService.getDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dish> obtainDish(@PathVariable Long id) {
        Dish dish = dishService.getDish(id);
        if (dish == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dish);
    }

    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestBody Dish dish) {
        return ResponseEntity.status(201).body(dishService.createDish(dish));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> editDish(@PathVariable Long id, @RequestBody Dish dish) {
        Dish editDish = dishService.putDish(id, dish);
        if (editDish == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editDish);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchDish(@PathVariable Long id, @RequestBody Dish dish){
        if(dishService.editDish(id, dish) == null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDish(@PathVariable Long id) {
        if (dishService.removeDish(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
