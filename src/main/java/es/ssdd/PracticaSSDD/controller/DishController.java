package es.ssdd.PracticaSSDD.controller;

import es.ssdd.PracticaSSDD.data.Dish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/dishes")
    public String listDishes(Model model) {
        model.addAttribute("dishes", dishes.values());
        return "dishes";
    }

    @GetMapping("/add-dish")
    public String showFormAddDishes(Model model) {
        model.addAttribute("dish", new Dish());
        return "add-dish";
    }

    @PostMapping("/add-dish")
    public String processFormAddDishes(Dish dish, Model model) {
        String success = createDish(dish);
        model.addAttribute("success", success);
        return "redirect:/dishes";
    }




}
