package es.ssdd.PracticaSSDD.controller;

import es.ssdd.PracticaSSDD.data.Ingredient;
import es.ssdd.PracticaSSDD.data.NutricionalTable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class NutricionalTableController {
    private AtomicLong counter = new AtomicLong();
    private ConcurrentMap<Long, NutricionalTable> tables = new ConcurrentHashMap<>();

    public String createTable(NutricionalTable table) {
        Long id = counter.incrementAndGet();
        table.setId(id);
        tables.put(id, table);
        return "Tabla creada con éxito";
    }
    public String editTable(NutricionalTable table){
        Long id = table.getId();
        tables.get(id).setCalories(table.getCalories());
        tables.get(id).setProtein(table.getProtein());
        tables.get(id).setCarbohydrates(table.getCarbohydrates());
        tables.get(id).setFats(table.getFats());
        return "Tabla editada con éxito";
    }
    public String deleteTable(NutricionalTable table){
        Long id = table.getId();
        tables.remove(id);
        return "Ingrediente borrado con éxito";
    }

    @GetMapping("/tables")
    public String getTables(Model model){
        model.addAttribute("tables", tables.values());
        model.addAttribute("success", " ");
        return "tables";
    }

    @GetMapping("/new-table")
    public String showFormNewTable(Model model){
        model.addAttribute("success", "");
        return "new-table";
    }

    @PostMapping("/new-table")
    public String processFormNewTable(NutricionalTable table, Model model){
        String success = createTable(table);
        model.addAttribute("success", success);
        return "new-table";
    }
    @GetMapping("/tables/details/{id}")
    public String detailedIngredient(@PathVariable Long id, Model model){
        model.addAttribute("table", tables.get(id));
        return "table";
    }

    @GetMapping("/tables/edit/{id}")
    public String editTableForm(@PathVariable Long id, Model model){
        model.addAttribute("success", " ");
        model.addAttribute("ingredient", tables.get(id));
        return "edit-table";
    }
    @PostMapping("/tables/edit/{id}")
    public String editTable(NutricionalTable table, Model model){
        String e = editTable(table);
        model.addAttribute("success", e);
        model.addAttribute("ingredient", tables.get(table.getId()));
        return "edit-table";
    }
    @GetMapping("/tables/delete/{id}")
    public String deleteTable(@PathVariable Long id, Model model){
        String e = deleteTable(tables.get(id));
        model.addAttribute("success", e);
        model.addAttribute("tables", this.tables.values());
        return "tables";
    }
}
