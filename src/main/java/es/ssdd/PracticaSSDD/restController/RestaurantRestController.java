package es.ssdd.PracticaSSDD.restController;


import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class RestaurantRestController {

    @Autowired
    private RestaurantService tableService;

    @GetMapping("/tables")
    public ResponseEntity<Collection<Restaurant>> getTables() {
        return ResponseEntity.ok(tableService.getTables());
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<Restaurant> getTable(@PathVariable Long id) {
        Restaurant table = tableService.getTable(id);
        if (table == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(table);
    }

    @PostMapping("/new-table")
    public ResponseEntity<Restaurant> createTable(@RequestBody Restaurant table) {
        return ResponseEntity.status(201).body(tableService.createTable(table));
    }

    @PutMapping("/edit-table/{id}")
    public ResponseEntity<Restaurant> editTable(@PathVariable Long id, @RequestBody Restaurant table) {
        Restaurant original = tableService.editTable(id, table);
        if (original == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(original);
    }

    @DeleteMapping("/delete-table/{id}")
    public ResponseEntity<Void> removeTable(@PathVariable Long id) {
        if (tableService.removeTable(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
