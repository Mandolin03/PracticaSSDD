package es.ssdd.PracticaSSDD.restController;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.repositories.DishRepository;
import es.ssdd.PracticaSSDD.repositories.IngredientRepository;
import es.ssdd.PracticaSSDD.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.MalformedParametersException;
import java.util.Collection;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientRestController {
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public ResponseEntity<Collection<Ingredient>> getIngredients() {
        Collection<Ingredient> ingredients = ingredientRepository.findAll();
        return ResponseEntity.ok(ingredients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        try {
            return ResponseEntity.status(201).body(ingredientService.createIngredient(ingredient));
        } catch (MalformedParametersException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        Ingredient editIngredient;
        try {
            editIngredient = ingredientService.putIngredient(id, ingredient);
        } catch (MalformedParametersException e) {
            return ResponseEntity.badRequest().build();
        }
        if (editIngredient == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(editIngredient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        Ingredient edited;
        try{
            edited = ingredientService.editIngredient(id, ingredient);
        }catch (MalformedParametersException e){
            return ResponseEntity.badRequest().build();
        }
        if (edited == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeIngredient(@PathVariable Long id) {
        if (ingredientService.removeIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
