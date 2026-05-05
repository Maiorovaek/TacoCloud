package tacos.data;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

//import org.springframework.data.domain.Persistable;
//import org.springframework.data.relational.core.mapping.Table;


@Data

//@Data неявно добавляет конструктор с обязательными аргументами, но,
// когда используется @NoArgsConstructor, этот конструктор удаляется


//@Entity //Ingredient помеченая @Entity - сущность JPA.
// Имя таблицы по умолчанию - Ingredient

@Table
@AllArgsConstructor//упростить создание объекта Ingredient со всеми инициализированными свойствам

//создание конструктора без аргументов,
// force=true сделать все свойства финальными c значениями null, 0 или false
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
//@RequiredArgsConstructor
public class Ingredient
//        implements Persistable<String >
{
   @Id
    private String id;
    private String name;
    private Type type;
//Persistable был нужен при работе с Spring Data JDBC, только чтобы определить,
// должен ли создаваться новый или обновляться существующий объект;
//             @Override
//             public boolean isNew() {
//                 return false;
//             }


    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
