package es.ssdd.PracticaSSDD.controller;

import es.ssdd.PracticaSSDD.data.Dish;
import es.ssdd.PracticaSSDD.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/dishes")
    public String listDishes(Model model) {
        model.addAttribute("dishes", dishService.getDishes());
        return "dishes";
    }

    @GetMapping("/add-dish")
    public String showFormAddDishes() {
        return "add-dish";
    }

    @PostMapping("/add-dish")
    public String processFormAddDishes(Dish dish, Model model) {
        String success = dishService.createDish(dish);
        model.addAttribute("success", success);
        return "add-dish";
    }
}
