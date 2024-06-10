package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParametersException;
import java.util.Collection;
import java.util.HashSet;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient createIngredient(Ingredient ingredient) {
        checkIngredient(ingredient);
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    public Ingredient getIngredient(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public Collection<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient editIngredient(Long id, Ingredient ingredient) {
        if (!ingredientRepository.existsById(id)) {
            return null;
        }
        Ingredient original = ingredientRepository.findById(id).get();
        if (ingredient.getCategory() != null) original.setCategory(ingredient.getCategory());
        if (ingredient.getName() != null) original.setName((ingredient.getName()));
        if (ingredient.getOrigin() != null) original.setOrigin((ingredient.getOrigin()));
        checkIngredient(original);
        ingredientRepository.save(original);
        return original;
    }

    public Ingredient putIngredient(Long id, Ingredient ingredient) {
        if (!ingredientRepository.existsById(id)) {
            return null;
        }
        checkIngredient(ingredient);
        ingredient.setId(id);
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    public boolean removeIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) return false;
        Ingredient i = ingredientRepository.findById(id).get();
        var dishes = new HashSet<>(i.getDishes());
        for (Dish d : dishes) {
            d.removeIngredient(i);
        }
        ingredientRepository.deleteById(id);
        return true;
    }

    private void checkIngredient(Ingredient ingredient) {
        if(ingredient.getCategory() == null ||
                ingredient.getName() == null ||
                ingredient.getOrigin() == null) {
            throw new MalformedParametersException("Los campos no pueden ser nulos");
        }
        if (ingredient.getCategory().isEmpty() ||
                ingredient.getName().isEmpty() ||
                ingredient.getOrigin().isEmpty()) {
            throw new MalformedParametersException("Los campos no pueden estar vacios - ING");
        }
    }
}
