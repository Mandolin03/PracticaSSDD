package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class IngredientService {
    private final Map<Long, Ingredient> ingredients = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Ingredient createIngredient(Ingredient ingredient) {
        long id = nextId.incrementAndGet();
        ingredient.setId(id);
        ingredients.put(id, ingredient);
        return ingredient;
    }

    public Ingredient obtainIngredient(Long id) {
        return ingredients.get(id);
    }

    public Collection<Ingredient> obtainAllIngredients() {

        return ingredients.values();
    }

    public Ingredient editIngredient(Long id, Ingredient ingredient) {
        if (!ingredients.containsKey(id)) {
            return null;
        }
        ingredient.setId(id);
        ingredients.put(id, ingredient);
        return ingredient;
    }

    public void removeIngredient(Long id) {
        ingredients.remove(id);
    }
}
