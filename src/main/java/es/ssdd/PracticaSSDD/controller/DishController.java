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
    public String cervezaDetalle(@PathVariable Long id, Model model){
        model.addAttribute("dish", dishes.get(id));
        return "dish";
    }

    @GetMapping("/dishes/edit/{id}")
    public String editarDishForm(@PathVariable Integer id, Model model){
        model.addAttribute("exito", " ");
        model.addAttribute("cerveza", dishes.get(id));
        return "editarCerveza";
    }
    @PostMapping("/dishes/edit/{id}")
    public String editarDish(Dish cerveza, Model model){
        String e = editDish(cerveza);
        model.addAttribute("exito", e);
        model.addAttribute("cerveza", dishes.get(cerveza.getId()));
        return "editarCerveza";
    }
    @GetMapping("/dishes/delete/{id}")
    public String borrarCerveza(@PathVariable Integer id, Model model){
        String e = deleteDish(dishes.get(id));
        model.addAttribute("exito", e);
        model.addAttribute("cervezas", this.dishes.values());
        return "cervezas";
    }

}
