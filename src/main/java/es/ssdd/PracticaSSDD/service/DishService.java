package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.data.Dish;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DishService {
    private final ConcurrentMap<Long, Dish> dishes = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public String createDish(Dish dish) {
        Long id = counter.incrementAndGet();
        dish.setId(id);
        dishes.put(id, dish);
        return "Plato creado con éxito";
    }

    public Collection<Dish> getDishes(){
        return dishes.values();
    }


}
