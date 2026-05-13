package tacos_jpa.repositories;

import org.springframework.data.repository.CrudRepository;
import tacos_jpa.entities.Ingredient;

public interface IngredientRepository
      extends CrudRepository<Ingredient, String>
{
////CRUD (Create, Read, Up- date, Delete – создать, прочитать, изменить, удалить)
//    Iterable<Ingredient> findAll();
//    Optional<Ingredient> findById(String id); - который необходим внутри IngredientByIdConverter
//    для поиска ингредиента по ID из чекбокса.

//    Ingredient save(Ingredient ingredient);
}
