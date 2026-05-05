package tacos.data;


//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.Repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository
        //extends CrudRepository<Ingredient, String>
{
////CRUD (Create, Read, Up- date, Delete – создать, прочитать, изменить, удалить)
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);



}
