package es.ssdd.PracticaSSDD.controller;

import es.ssdd.PracticaSSDD.data.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class IngredientController {
    private AtomicLong counter = new AtomicLong();
    private ConcurrentMap<Long, Ingredient> ingredients = new ConcurrentHashMap<>();



    @GetMapping("/ingredients")
    public String getIngredients(Model model){
        model.addAttribute("ingredients", ingredients.values());
        return "ingredients";
    }

    @GetMapping("/add-ingredient")
    public String showFormAddIngredient(){

        return "add-ingredient";
    }

    public String processFormAddIngredient(){

        return "redirect:/ingredients";
    }
}
