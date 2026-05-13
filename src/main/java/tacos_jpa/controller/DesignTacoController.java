package tacos_jpa.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos_jpa.entities.Ingredient.Type;
import tacos_jpa.entities.TacoOrder;
import tacos_jpa.entities.Ingredient;
import tacos_jpa.entities.Taco;
import tacos_jpa.services.IngredientService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
//объект TacoOrder, объявленный в классе чуть ниже, должен поддерживаться на уровне сеанса.
// Это важно, потому что создание тако также является первым шагом в создании заказа, и
// созданный нами заказ необходимо будет перенести в сеанс, охватывающий несколько запросов.

public class DesignTacoController {

    private final IngredientService ingredientService;

    public DesignTacoController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = ingredientService.findAllIngredients();
        System.out.println("DEBUG: Всего в базе найдено: " + ingredients.size());
        Type[] types = Type.values();

        for (Type type : types) {
            List<Ingredient> filtered = (List<Ingredient>) filterByType(ingredients, type);
            System.out.println("Type: " + type + ", Count: " + filtered.size());
            model.addAttribute(type.toString().toLowerCase(),
                    filtered);
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing taco: {}", taco);
        tacoOrder.addTaco(taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type
    ) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
