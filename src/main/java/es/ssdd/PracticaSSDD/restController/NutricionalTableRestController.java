package es.ssdd.PracticaSSDD.restController;


import es.ssdd.PracticaSSDD.entities.NutricionalTable;
import es.ssdd.PracticaSSDD.service.NutricionalTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class NutricionalTableRestController {

    @Autowired
    private NutricionalTableService tableService;

    @GetMapping("/tables")
    public ResponseEntity<Collection<NutricionalTable>> getTables() {
        return ResponseEntity.ok(tableService.getTables());
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<NutricionalTable> getTable(@PathVariable Long id) {
        NutricionalTable table = tableService.getTable(id);
        if (table == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(table);
    }

    @PostMapping("/new-table")
    public ResponseEntity<NutricionalTable> createTable(@RequestBody NutricionalTable table) {
        return ResponseEntity.status(201).body(tableService.createTable(table));
    }

    @PutMapping("/edit-table/{id}")
    public ResponseEntity<NutricionalTable> editTable(@PathVariable Long id, @RequestBody NutricionalTable table) {
        NutricionalTable original = tableService.editTable(id, table);
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
