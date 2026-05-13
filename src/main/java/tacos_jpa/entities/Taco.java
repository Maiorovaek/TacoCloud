package tacos_jpa.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "name must be at least 5 characters long")
    private String name;

    @ManyToOne
    @JoinColumn(name = "taco_order")
    private TacoOrder tacoOrder;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    //заимосвязь между Taco и списком ингредиентов Ingredient
    //Объект Taco может включать в список несколько объектов Ingredient,
    // а один объект Ingredient может быть частью списков в нескольких объектах Taco.
    @ManyToMany
    @JoinTable(
            name = "taco_ingredients",
            joinColumns = @JoinColumn(name = "taco_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);

    }
}
