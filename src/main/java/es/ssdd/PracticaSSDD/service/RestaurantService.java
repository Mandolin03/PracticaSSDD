package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Restaurant;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RestaurantService {
    private final Map<Long, Restaurant> tables = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Restaurant createTable(Restaurant table) {
        long id = nextId.incrementAndGet();
        table.setId(id);
        tables.put(id, table);
        return table;
    }

    public Restaurant getTable(Long id) {
        return tables.get(id);
    }

    public Collection<Restaurant> getTables() {
        return tables.values();
    }

    public Restaurant editTable(Long id, Restaurant table) {

        if (!tables.containsKey(id)) {
            return null;
        }
        Restaurant original = tables.get(id);
        if(table.getCalories() != null)original.setCalories(table.getCalories());
        if(table.getCarbohydrates() != null)original.setCarbohydrates(table.getCarbohydrates());
        if(table.getProtein() != null)original.setProtein(table.getProtein());
        if(table.getFats() != null)original.setFats(table.getFats());

        tables.put(id, original);
        return original;
    }

    public boolean removeTable(Long id) {
        return tables.remove(id) != null;
    }
}
