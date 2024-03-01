package es.ssdd.PracticaSSDD.restController;

import es.ssdd.PracticaSSDD.entities.Ingredient;
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
        public ResponseEntity<Collection<Ingredient>> getIngredients() {
            return ResponseEntity.ok(ingredientService.getIngredients());
        }

        @GetMapping("/ingredients/{id}")
        public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id) {
            Ingredient ingredient = ingredientService.getIngredient(id);
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
            if (ingredientService.removeIngredient(id)) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }
    }
