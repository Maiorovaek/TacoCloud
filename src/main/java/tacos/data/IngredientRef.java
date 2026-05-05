package tacos.data;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
//import org.springframework.data.relational.core.mapping.Column;

@Data
@Table
public class IngredientRef {
  //  @Column("ingredient")
    private final String ingredient;

//    public IngredientRef(String ingredient) {
//        this.ingredient = ingredient;
//    }
}
