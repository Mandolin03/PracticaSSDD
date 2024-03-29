package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Ingredient;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParametersException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class IngredientService {
    private final Map<Long, Ingredient> ingredients = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Ingredient createIngredient(Ingredient ingredient) {
        checkIngredient(ingredient);
        long id = nextId.getAndIncrement();
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
        checkIngredient(ingredient);
        Ingredient original = ingredients.get(id);
        if(ingredient.getCategory() != null)original.setCategory(ingredient.getCategory());
        if(ingredient.getName() != null)original.setName((ingredient.getName()));
        if(ingredient.getOrigin() != null)original.setOrigin((ingredient.getOrigin()));
        ingredients.put(id, original);
        return original;
    }

    public Ingredient putIngredient(Long id, Ingredient ingredient) {
        if (!ingredients.containsKey(id)) {
            return null;
        }
        checkIngredient(ingredient);
        ingredient.setId(id);
        ingredients.put(id, ingredient);
        return ingredient;
    }

    public boolean removeIngredient(Long id) {
        return ingredients.remove(id) != null;
    }

    private void checkIngredient(Ingredient ingredient){
        if((ingredient.getCategory() != null && ingredient.getCategory().isEmpty()) ||
               (ingredient.getName() != null && ingredient.getName().isEmpty())||
                (ingredient.getOrigin() != null && ingredient.getOrigin().isEmpty()))
        {
            throw new MalformedParametersException("Los campos no pueden estar vacios");
        }
    }
}
