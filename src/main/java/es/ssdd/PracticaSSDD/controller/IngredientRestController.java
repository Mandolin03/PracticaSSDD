package es.ssdd.PracticaSSDD.controller;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.service.DishService;
import es.ssdd.PracticaSSDD.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class IngredientRestController {


        @Autowired
        private IngredientService ingredientService;

        @GetMapping("/ingredients")
        public ResponseEntity<Collection<Ingredient>> obtainAllIngredients() {
            return ResponseEntity.ok(ingredientService.obtainAllIngredients());
        }

        @GetMapping("/ingredients/{id}")
        public ResponseEntity<Ingredient> obtainIngredient(@PathVariable Long id) {
            Ingredient ingredient = ingredientService.obtainIngredient(id);
            if (ingredient == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(ingredient);
        }

        @PostMapping("/new-ingredient")
        public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
            return ResponseEntity.status(201).body(ingredientService.createIngredient(ingredient));
        }

        @PutMapping("/edit-ingredient/{id}")
        public ResponseEntity<Ingredient> editIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
            Ingredient editIngredient = ingredientService.editIngredient(id, ingredient);
            if (editIngredient == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(editIngredient);
        }

        @DeleteMapping("/delete-ingredient/{id}")
        public ResponseEntity<Void> removeIngredient(@PathVariable Long id) {
            ingredientService.removeIngredient(id);
            return ResponseEntity.ok().build();
        }
    }
