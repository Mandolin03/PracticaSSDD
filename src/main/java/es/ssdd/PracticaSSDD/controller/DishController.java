package es.ssdd.PracticaSSDD.controller;

import es.ssdd.PracticaSSDD.entities.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import es.ssdd.PracticaSSDD.service.DishService;

@Controller
public class DishController {

    @Autowired
    private DishService dishService;

    /*public String createDish(Dish dish) {
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
    } */

    @GetMapping("/dishes")
    public String listDishes(Model model) {
        model.addAttribute("dishes", dishService.obtainAllDishes());
        model.addAttribute("success", " ");
        return "dishes/dishes";
    }

    @GetMapping("/new-dish")
    public String showFormNewDish(Model model) {
        model.addAttribute("success", "");
        return "dishes/new-dish";
    }

    @PostMapping("/new-dish")
    public String processFormNewDish(Dish dish) {
        dishService.createDish(dish);
        return "redirect:/dishes";
    }


    @GetMapping("/dishes/details/{id}")
    public String detailedDish(@PathVariable Long id, Model model){
        model.addAttribute("dish", dishService.obtainDish(id));
        return "dishes/dish";
    }

    @GetMapping("/dishes/edit/{id}")
    public String editDishForm(@PathVariable Long id, Model model){
        model.addAttribute("success", "");
        model.addAttribute("dish", dishService.obtainDish(id));
        return "dishes/edit-dish";
    }
    @PostMapping("/dishes/edit/{id}")
    public String editDish(Dish dish){
        dishService.editDish(dish.getId(), dish);
        return "redirect:/dishes";
    }
    @GetMapping("/dishes/delete/{id}")
    public String deleteDish(@PathVariable Long id){
        dishService.removeDish(id);
        return "redirect:/dishes";
    }

}
