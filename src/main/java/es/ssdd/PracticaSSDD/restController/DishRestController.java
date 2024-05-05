package es.ssdd.PracticaSSDD.restController;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.DishDTO;
import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.service.IngredientService;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.ssdd.PracticaSSDD.service.DishService;

import java.lang.reflect.MalformedParametersException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishRestController {
    @Autowired
    private DishService dishService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping
    public ResponseEntity<Collection<DishDTO>> obtainAllDishes() {
        List<DishDTO> dishes = new ArrayList<>();
        for (Dish dish : dishService.getDishes()) {
            DishDTO dto = new DishDTO();
            dto.setId(dish.getId());
            dto.setName(dish.getName());
            dto.setCategory(dish.getCategory());
            dto.setPrice(dish.getPrice());
            if(dish.getRestaurant() == null)dto.setRestaurant(-1L);
            else dto.setRestaurant(dish.getRestaurant().getId());
            List<Long> ingredients = new ArrayList<>();
            for (Ingredient i : dish.getIngredients()) {
                ingredients.add(i.getId());
            }
            dto.setIngredients(ingredients.toArray(new Long[0]));
            dishes.add(dto);
        }
        return ResponseEntity.ok(dishes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> obtainDish(@PathVariable Long id) {
        Dish dish = dishService.getDish(id);
        if (dish == null) {
            return ResponseEntity.notFound().build();
        }
        DishDTO dto = new DishDTO();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setCategory(dish.getCategory());
        dto.setPrice(dish.getPrice());
        if (dish.getRestaurant() == null) dto.setRestaurant(-1L);
        else dto.setRestaurant(dish.getRestaurant().getId());
        List<Long> ingredients = new ArrayList<>();
        for (Ingredient i : dish.getIngredients()) {
            ingredients.add(i.getId());
        }
        dto.setIngredients(ingredients.toArray(new Long[0]));
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<DishDTO> createDish(@RequestBody DishDTO dto) {
        try {
            Dish dish = new Dish();
            dish.setName(dto.getName());
            dish.setCategory(dto.getCategory());
            dish.setPrice(dto.getPrice());
            dish.setRestaurant(restaurantService.getRestaurant(dto.getRestaurant()));
            if(dto.getIngredients().length == 0)throw new MalformedParametersException();
            for (Long id : dto.getIngredients()) {
                dish.addIngredient(ingredientService.getIngredient(id));
            }
            dishService.createDish(dish);
            dto.setId(dish.getId());
            if (dish.getRestaurant() == null) dto.setRestaurant(-1L);
            return ResponseEntity.status(201).body(dto);
        } catch (MalformedParametersException | NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDTO> editDish(@PathVariable Long id, @RequestBody DishDTO dto) {
        Dish editDish;
        try {
            Dish dish = new Dish();
            dish.setName(dto.getName());
            dish.setCategory(dto.getCategory());
            dish.setPrice(dto.getPrice());
            dish.setRestaurant(restaurantService.getRestaurant(dto.getRestaurant()));
            for (Long i : dto.getIngredients()) {
                dish.addIngredient(ingredientService.getIngredient(i));
            }

            editDish = dishService.putDish(id, dish);
        } catch (MalformedParametersException e) {
            return ResponseEntity.badRequest().build();
        }
        if (editDish == null) {
            return ResponseEntity.notFound().build();
        }
        dto.setId(editDish.getId());
        if (editDish.getRestaurant() == null) dto.setRestaurant(-1L);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchDish(@PathVariable Long id, @RequestBody DishDTO dto) {
        Dish edited;
        try {
            Dish dish = new Dish();
            dish.setName(dto.getName());
            dish.setCategory(dto.getCategory());
            dish.setPrice(dto.getPrice());
            if(dto.getRestaurant() != null){
                dish.setRestaurant(restaurantService.getRestaurant(dto.getRestaurant()));
            }
            else {
                dish.setRestaurant(dishService.getDish(id).getRestaurant());
            }
            if(dto.getIngredients() != null) {
                if(dto.getIngredients().length == 0)return ResponseEntity.badRequest().build();
                for (Long i : dto.getIngredients()) {
                    dish.addIngredient(ingredientService.getIngredient(i));
                }
            }
            else{
                dish.setIngredients(dishService.getDish(id).getIngredients());
            }
            edited = dishService.editDish(id, dish);
        } catch (MalformedParametersException e) {
            return ResponseEntity.badRequest().build();
        }
        if (edited == null) return ResponseEntity.notFound().build();
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
