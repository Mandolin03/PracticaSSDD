package es.ssdd.PracticaSSDD.webController;


import es.ssdd.PracticaSSDD.entities.Ingredient;
import es.ssdd.PracticaSSDD.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {
 @Autowired
 private IngredientService ingredientService;

    @GetMapping("/ingredients")
    public String getIngredients(Model model){
        model.addAttribute("ingredients", ingredientService.getIngredients());
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
        if (ingredientService.getIngredient(id) != null){
            model.addAttribute("ingredient", ingredientService.getIngredient(id));
            return "ingredients/ingredient";
        } else {
            return "redirect:/ingredients";
        }
    }

    @GetMapping("/ingredients/edit/{id}")
    public String editDishForm(@PathVariable Long id, Model model){
        if (ingredientService.getIngredient(id) != null){
            model.addAttribute("success", " ");
            model.addAttribute("ingredient", ingredientService.getIngredient(id));
            return "ingredients/edit-ingredient";
        } else {
            return "redirect:/ingredients";
        }

    }
    @PostMapping("/ingredients/edit/{id}")
    public String editIngredient(Ingredient ingredient){
        ingredientService.editIngredient(ingredient.getId(), ingredient);
        return "redirect:/ingredients";
    }
    @GetMapping("/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable Long id){
        ingredientService.removeIngredient(id);
        return "redirect:/ingredients";
    }
}
