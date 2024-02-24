package es.ssdd.PracticaSSDD.controller;


import es.ssdd.PracticaSSDD.data.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String editIngredient(Ingredient ingredient){
        Long id = ingredient.getId();
        ingredients.get(id).setName(ingredient.getName());
        ingredients.get(id).setCategory(ingredient.getCategory());
        return "Ingrediente editado con éxito";
    }
    public String deleteIngredient(Ingredient ingredient){
        Long id = ingredient.getId();
        ingredients.remove(id);
        return "Ingrediente borrado con éxito";
    }

    @GetMapping("/ingredients")
    public String getIngredients(Model model){
        model.addAttribute("ingredients", ingredients.values());
        model.addAttribute("success", " ");
        return "ingredients/ingredients";
    }

    @GetMapping("/new-ingredient")
    public String showFormNewIngredient(Model model){
        model.addAttribute("success", "");
        return "ingredients/new-ingredient";
    }

    @PostMapping("/new-ingredient")
    public String processFormNewIngredient(Ingredient ingredient, Model model){
        String success = createIngredient(ingredient);
        model.addAttribute("success", success);
        return "redirect:/ingredients";
    }
    @GetMapping("/ingredients/details/{id}")
    public String detailedIngredient(@PathVariable Long id, Model model){
        model.addAttribute("ingredient", ingredients.get(id));
        return "ingredients/ingredient";
    }

    @GetMapping("/ingredients/edit/{id}")
    public String editDishForm(@PathVariable Long id, Model model){
        model.addAttribute("success", " ");
        model.addAttribute("ingredient", ingredients.get(id));
        return "ingredients/edit-ingredient";
    }
    @PostMapping("/ingredients/edit/{id}")
    public String editIngredient(Ingredient ingredient, Model model){
        String e = editIngredient(ingredient);
        model.addAttribute("success", e);
        model.addAttribute("ingredient", ingredients.get(ingredient.getId()));
        return "redirect:/ingredients";
    }
    @GetMapping("/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable Long id, Model model){
        String e = deleteIngredient(ingredients.get(id));
        model.addAttribute("success", e);
        model.addAttribute("ingredients", this.ingredients.values());
        return "redirect:/ingredients";
    }
}
