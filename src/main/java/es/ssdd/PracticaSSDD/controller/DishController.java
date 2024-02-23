package es.ssdd.PracticaSSDD.controller;

import es.ssdd.PracticaSSDD.data.Dish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class DishController {

    private final ConcurrentMap<Long, Dish> dishes = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public String createDish(Dish dish) {
        Long id = counter.incrementAndGet();
        dish.setId(id);
        dishes.put(id, dish);
        return "Plato creado con éxito";
    }
    public String editDish(Dish dish){
        Long id = dish.getId();
        dishes.get(id).setName(dish.getName());
        dishes.get(id).setCategory(dish.getCategory());
        dishes.get(id).setPrice(dish.getPrice());
        return "Plato editado con éxito";
    }
    public String deleteDish(Dish dish){
        Long id = dish.getId();
        dishes.remove(id);
        return "Plato borrado con éxito";
    }

    @GetMapping("/dishes")
    public String listDishes(Model model) {
        model.addAttribute("dishes", dishes.values());
        model.addAttribute("success", " ");
        return "dishes";
    }

    @GetMapping("/new-dish")
    public String showFormNewDish(Model model) {
        model.addAttribute("success", "");
        return "new-dish";
    }

    @PostMapping("/new-dish")
    public String processFormNewDish(Dish dish, Model model) {
        String success = createDish(dish);
        model.addAttribute("success", success);
        return "new-dish";
    }


    @GetMapping("/dishes/details/{id}")
    public String detailedDish(@PathVariable Long id, Model model){
        model.addAttribute("dish", dishes.get(id));
        return "dish";
    }

    @GetMapping("/dishes/edit/{id}")
    public String editDishForm(@PathVariable Long id, Model model){
        model.addAttribute("success", " ");
        model.addAttribute("dish", dishes.get(id));
        return "edit-dish";
    }
    @PostMapping("/dishes/edit/{id}")
    public String editDish(Dish dish, Model model){
        String e = editDish(dish);
        model.addAttribute("success", e);
        model.addAttribute("dish", dishes.get(dish.getId()));
        return "edit-dish";
    }
    @GetMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id, Model model){
        String e = deleteDish(dishes.get(id));
        model.addAttribute("success", e);
        model.addAttribute("dish", this.dishes.values());
        return "dishes";
    }

}
