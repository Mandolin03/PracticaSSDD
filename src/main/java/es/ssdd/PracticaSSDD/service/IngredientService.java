package es.ssdd.PracticaSSDD.service;

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

    public Ingredient getIngredient(Long id) {
        return ingredients.get(id);
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients.values();
    }

    public Ingredient editIngredient(Long id, Ingredient ingredient) {
        if (!ingredients.containsKey(id)) {
            return null;
        }
        Ingredient original = ingredients.get(id);
        if(ingredient.getCategory() != null)original.setCategory(ingredient.getCategory());
        if(ingredient.getName() != null)original.setName((ingredient.getName()));
        ingredients.put(id, original);
        return original;
    }

    public boolean removeIngredient(Long id) {
        return ingredients.remove(id) != null;
    }
}
