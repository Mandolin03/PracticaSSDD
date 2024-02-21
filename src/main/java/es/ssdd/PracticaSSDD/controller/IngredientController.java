package es.ssdd.PracticaSSDD.controller;

import es.ssdd.PracticaSSDD.data.Dish;
import es.ssdd.PracticaSSDD.data.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class IngredientController {
    private AtomicLong counter = new AtomicLong();
    private ConcurrentMap<Long, Ingredient> ingredients = new ConcurrentHashMap<>();

    public String createIngredient(Ingredient ingredient) {
        Long id = counter.incrementAndGet();
        ingredient.setId(id);
        ingredients.put(id, ingredient);
        return "Ingrediente creado con éxito";
    }

    @GetMapping("/ingredients")
    public String getIngredients(Model model){
        model.addAttribute("ingredients", ingredients.values());
        return "ingredients";
    }

    @GetMapping("/add-ingredient")
    public String showFormAddIngredient(Model model){
        model.addAttribute("ingredient", new Ingredient());
        return "add-ingredient";
    }

    @PostMapping("/add-ingredient")
    public String processFormAddIngredient(Ingredient ingredient, Model model){
        String success = createIngredient(ingredient);
        model.addAttribute("success", success);
        return "redirect:/ingredients";
    }
}
