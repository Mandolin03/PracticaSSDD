package es.ssdd.PracticaSSDD.webController;

import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RestaurantController {
    @Autowired
    private RestaurantService tableService;

    @GetMapping("/tables")
    public String getTables(Model model){
        model.addAttribute("tables", tableService.getTables());
        model.addAttribute("success", " ");
        return "tables/tables";
    }

    @GetMapping("/new-table")
    public String showFormNewTable(Model model){
        model.addAttribute("success", "");
        return "tables/new-table";
    }

    @PostMapping("/new-table")
    public String processFormNewTable(Restaurant table){
        tableService.createTable(table);
        return "redirect:/tables";
    }
    @GetMapping("/tables/details/{id}")
    public String detailedIngredient(@PathVariable Long id, Model model){
        if (tableService.getTable(id) != null){
            model.addAttribute("table", tableService.getTable(id));
            return "tables/table";
        } else {
            return "redirect:/tables";
        }

    }

    @GetMapping("/tables/edit/{id}")
    public String editTableForm(@PathVariable Long id, Model model){
        if (tableService.getTable(id) != null){
            model.addAttribute("success", "");
            model.addAttribute("table", tableService.getTable(id));
            return "tables/edit-table";
        } else {
            return "redirect:/tables";
        }

    }
    @PostMapping("/tables/edit/{id}")
    public String editTable(Restaurant table){
        tableService.editTable(table.getId(), table);
        return "redirect:/tables";
    }
    @GetMapping("/tables/delete/{id}")
    public String deleteTable(@PathVariable Long id){
        tableService.removeTable(id);
        return "redirect:/tables";
    }
}
