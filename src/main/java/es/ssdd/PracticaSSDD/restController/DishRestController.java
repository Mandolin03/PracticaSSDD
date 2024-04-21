package es.ssdd.PracticaSSDD.restController;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.repositories.DishRepository;
import es.ssdd.PracticaSSDD.service.IngredientService;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.ssdd.PracticaSSDD.service.DishService;

import java.lang.reflect.MalformedParametersException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/dishes")
public class DishRestController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    @Autowired
    private DishRepository dishRepository;

    @GetMapping
    public ResponseEntity<Collection<Dish>> obtainAllDishes() {
        Collection<Dish> dishes = dishRepository.findAll();
        return ResponseEntity.ok(dishes);
    }

    /* ANTERIOR
    @GetMapping("/{id}")
    public ResponseEntity<Dish> obtainDish(@PathVariable Long id) {
        Dish dish = dishService.getDish(id);
        if (dish == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dish);
    } */

    @GetMapping("/{id}")
    public ResponseEntity<Dish> obtainDish(@PathVariable Long id) {
        Optional<Dish> optionalDish = dishRepository.findById(id);
        if (optionalDish.isPresent()) {
            Dish dish = optionalDish.get();
            dish.getIngredients().size();
            return ResponseEntity.ok(dish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestBody Dish dish) {
        try{
            return ResponseEntity.status(201).body(dishService.createDish(dish));
        }catch (MalformedParametersException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dish> editDish(@PathVariable Long id, @RequestBody Dish dish) {
        Dish editDish;
        try{
            editDish = dishService.putDish(id, dish);
        }catch (MalformedParametersException e){
            return ResponseEntity.badRequest().build();
        }
        if (editDish == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editDish);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchDish(@PathVariable Long id, @RequestBody Dish dish){
        Dish edited;
        try{
            edited = dishService.editDish(id, dish);
        }catch (MalformedParametersException e){
            return ResponseEntity.badRequest().build();
        }
        if(edited == null)return ResponseEntity.notFound().build();
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
