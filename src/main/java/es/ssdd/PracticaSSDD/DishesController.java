package es.ssdd.PracticaSSDD;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class DishesController {

        private Map<Long, es.ssdd.PracticaSSDD.Dishes> dishes = new ConcurrentHashMap<>();
        private AtomicLong counter = new AtomicLong();

        @GetMapping("/add-dishes")
        public String listDishes(Model model) {
            model.addAttribute("dishes", dishes.values());
            return "add-dishes";

        }

    @GetMapping("/dishes/add")
    public String showFormAddDishes(Model model) {
        model.addAttribute("dishes", new Dishes());
        return "add-dishes";
    }
}
