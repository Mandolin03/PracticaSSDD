package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.NutricionalTable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NutricionalTableService {
    private final Map<Long, NutricionalTable> tables = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public NutricionalTable createTable(NutricionalTable table) {
        long id = nextId.incrementAndGet();
        table.setId(id);
        tables.put(id, table);
        return table;
    }

    public NutricionalTable getTable(Long id) {
        return tables.get(id);
    }

    public Collection<NutricionalTable> getTables() {
        return tables.values();
    }

    public NutricionalTable editTable(Long id, NutricionalTable table) {

        if (!tables.containsKey(id)) {
            return null;
        }
        NutricionalTable original = tables.get(id);
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
