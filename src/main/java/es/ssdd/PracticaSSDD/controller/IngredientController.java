package es.ssdd.PracticaSSDD.controller;


import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.service.DishService;
import es.ssdd.PracticaSSDD.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
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

 /*   public String createIngredient(Ingredient ingredient) {
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
*/
 @Autowired
 private IngredientService ingredientService;

    @GetMapping("/ingredients")
    public String getIngredients(Model model){
        model.addAttribute("ingredients", ingredientService.obtainAllIngredients());
        model.addAttribute("success", " ");
        return "ingredients/ingredients";
    }

    @GetMapping("/new-ingredient")
    public String showFormNewIngredient(Model model){
        model.addAttribute("success", "");
        return "ingredients/new-ingredient";
    }

    @PostMapping("/new-ingredient")
    public String processFormNewIngredient(Ingredient ingredient){
        ingredientService.createIngredient(ingredient);
        return "redirect:/ingredients";
    }
    @GetMapping("/ingredients/details/{id}")
    public String detailedIngredient(@PathVariable Long id, Model model){
        model.addAttribute("ingredient", ingredientService.obtainIngredient(id));
        return "ingredients/ingredient";
    }

    @GetMapping("/ingredients/edit/{id}")
    public String editDishForm(@PathVariable Long id, Model model){
        model.addAttribute("success", " ");
        model.addAttribute("ingredient", ingredientService.obtainIngredient(id));
        return "ingredients/edit-ingredient";
    }
    @PostMapping("/ingredients/edit/{id}")
    public String editIngredient(Ingredient ingredient){
        ingredientService.editIngredient(ingredient.getId(), ingredient);
        return "redirect:/ingredients";
    }
    @GetMapping("/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable Long id, Model model){
        ingredientService.removeIngredient(id);
        return "redirect:/ingredients";
    }
}
