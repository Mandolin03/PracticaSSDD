package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParametersException;
import java.util.Collection;

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
        return ingredientRepository.getReferenceById(id);
    }

    public Collection<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient editIngredient(Long id, Ingredient ingredient) {
        if (!ingredientRepository.existsById(id)) {
            return null;
        }
        checkIngredient(ingredient);
        Ingredient original = ingredientRepository.getReferenceById(id);
        if(ingredient.getCategory() != null)original.setCategory(ingredient.getCategory());
        if(ingredient.getName() != null)original.setName((ingredient.getName()));
        if(ingredient.getOrigin() != null)original.setOrigin((ingredient.getOrigin()));
        ingredientRepository.save(original);
        return original;
    }

    public Ingredient putIngredient(Long id, Ingredient ingredient) {
        if (!ingredientRepository.existsById(id)){
            return null;
        }
        checkIngredient(ingredient);
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    public boolean removeIngredient(Long id) {
        ingredientRepository.deleteById(id);
        return true;
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
