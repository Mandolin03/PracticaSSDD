package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Dish;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DishService {
    private final Map<Long, Dish> dishes = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Dish createDish(Dish dish) {
        long id = nextId.incrementAndGet();
        dish.setId(id);
        dishes.put(id, dish);
        return dish;
    }

    public Dish obtainDish(Long id) {
        return dishes.get(id);
    }

    public Collection<Dish> obtainAllDishes() {
        return dishes.values();
    }

    public Dish editDish(Long id, Dish dish) {
        if (!dishes.containsKey(id)) {
            return null;
        }
        dish.setId(id);
        dishes.put(id, dish);
        return dish;
    }

    public void removeDish(Long id) {
        dishes.remove(id);
    }
}
