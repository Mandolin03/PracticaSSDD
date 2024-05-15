package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParametersException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public Dish createDish(Dish dish) {
        checkDish(dish);
        dishRepository.save(dish);
        return dish;
    }

    public Dish getDish(Long id) {
        return dishRepository.getReferenceById(id);
    }

    public Collection<Dish> getDishes() {
        return dishRepository.findAll();
    }

    public Dish editDish(Long id, Dish dish) {

        if (!dishRepository.existsById(id)) {
            return null;
        }
        checkDish(dish);
        Dish original = dishRepository.getReferenceById(id);
        if (dish.getCategory() != null) original.setCategory(dish.getCategory());
        if (dish.getName() != null) original.setName((dish.getName()));
        if (dish.getPrice() != null) original.setPrice((dish.getPrice()));

        original.setRestaurant(dish.getRestaurant());
        original.setIngredients(dish.getIngredients());
        dishRepository.save(original);
        return original;
    }

    public Dish putDish(Long id, Dish dish) {
        if (!dishRepository.existsById(id)) return null;
        checkDish(dish);
        dish.setId(id);
        return dishRepository.save(dish);
    }

    public boolean removeDish(Long id) {
        if(!dishRepository.existsById(id)) return false;
        Dish d = dishRepository.findById(id).get();
        var ings = new HashSet<>(d.getIngredients());
        for(Ingredient i : ings){
            i.removeDish(d);
        }
        dishRepository.deleteById(id);
        return true;
    }


    private void checkDish(Dish dish) {
        if(dish.getCategory() == null ||
                dish.getName() == null ||
                dish.getPrice() == null) {
            throw new MalformedParametersException("Los campos no pueden estar vacios");
        }
        if (dish.getCategory().isEmpty() ||dish.getName().isEmpty() ||dish.getIngredients().isEmpty()) {
            throw new MalformedParametersException("Los campos no pueden estar vacios");
        }
        if (dish.getPrice() < 0) {
            throw new MalformedParametersException("El precio debe ser un real positvo");
        }
    }
}
