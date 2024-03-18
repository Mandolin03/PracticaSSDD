package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Dish;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParametersException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DishService {
    private final Map<Long, Dish> dishes = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Dish createDish(Dish dish) {
        checkDish(dish);
        long id = nextId.getAndIncrement();
        dish.setId(id);
        dishes.put(id, dish);
        return dish;
    }

    public Dish getDish(Long id) {
        return dishes.get(id);
    }

    public Collection<Dish> getDishes() {
        return dishes.values();
    }

    public Dish editDish(Long id, Dish dish) {

        if (!dishes.containsKey(id)) {
            return null;
        }
        checkDish(dish);
        Dish original = dishes.get(id);
        if (dish.getCategory() != null) original.setCategory(dish.getCategory());
        if (dish.getName() != null) original.setName((dish.getName()));
        if (dish.getPrice() != null) original.setPrice((dish.getPrice()));
        dishes.put(id, original);

        return original;
    }

    public Dish putDish(Long id, Dish dish) {
        if (!dishes.containsKey(id)) {
            return null;
        }
        checkDish(dish);
        dish.setId(id);
        dishes.put(id, dish);
        return dish;
    }

    public boolean removeDish(Long id) {
        return dishes.remove(id) != null;
    }


    private void checkDish(Dish dish) {
        if ((dish.getCategory() != null && dish.getCategory().isEmpty()) || (dish.getName() != null && dish.getName().isEmpty())) {
            throw new MalformedParametersException("Los campos no pueden estar vacios");
        }
        if (dish.getPrice() != null && dish.getPrice() < 0) {
            throw new MalformedParametersException("El precio debe ser un real positvo");
        }
    }
}
