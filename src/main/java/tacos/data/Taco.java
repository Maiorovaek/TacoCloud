package tacos.data;

//import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
//import org.springframework.data.relational.core.mapping.MappedCollection;
//import org.springframework.data.relational.core.mapping.Table;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@Entity
@Table
public class Taco {
  //
  @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   private Date createdAt = new Date();
    @NotNull
    @Size(min = 5, message = "name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    //заимосвязь между Taco и списком ингредиентов Ingredient
    //Объект Taco может включать в список несколько объектов Ingredient,
    // а один объект Ingredient может быть частью списков в нескольких объектах Taco.
 //   @ManyToMany
   // @MappedCollection(idColumn = "taco", keyColumn = "taco_key")
    private List<IngredientRef> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(new IngredientRef(ingredient.getId()));

    }
}
