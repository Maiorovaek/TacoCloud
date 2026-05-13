package tacos_jpa.services;

import tacos_jpa.entities.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    List<Ingredient> findAllIngredients();

    Optional<Ingredient> findIngredientById(String id);
}
